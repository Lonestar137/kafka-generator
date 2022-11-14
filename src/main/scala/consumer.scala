package org.goldteam.kafka.consumer;

import constants.Output.{HELPTEXT, NO_ARG_INFO}

import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.mutable.ListBuffer
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import play.api.libs.json._
import logging.OutputFunctions

object SparkKafkaConsumer {
  val spark = SparkSession.builder
    .master("local[*]")
    .appName("SparkKafkaConsumer")
    .getOrCreate()

  def consumer(topic: String = "test"): Unit = {
    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put(
      "key.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer"
    )
    properties.put(
      "value.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer"
    )
    properties.put("group.id", topic)
    properties.put("enable.auto.commit", "true")
    properties.put("auto.commit.interval.ms", "1000")
    properties.put("auto.offset.reset", "earliest")

    val kafkaConsumer = new KafkaConsumer(properties)
    val topics = List(topic)
    var subscribed = true

    try {
      // Note: Redundant subscription, not necessary, leaving for example purposes.
      kafkaConsumer.subscribe(topics.asJava)
      while (subscribed) {
        val records = kafkaConsumer.poll(100)
        for (record <- records.asScala) {
          val record_string: String = record.value()

          val jsonD: JsValue = Json.parse(record_string)
          println("Key():" + jsonD("order_id"))
          println(jsonD)
        }
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      kafkaConsumer.close()
    }
  }

  object writeTo {
    OutputFunctions.log("APP START")
    OutputFunctions.log("CONSUMER START")

    // specific implementations of consumer
    def psql(
        bootstrap: String = "localhost:9092",
        db_host: String = "localhost:5432",
        db: String,
        user: String,
        pass: String,
        topic: String = "",
        topics: String = ""
    ): Unit = {
      import spark.sqlContext.implicits._

      // TODO read data to postgres from kafka using spark
      def consumer(topic: String = "test", break: Boolean = false): Unit = {
        val properties = new Properties()

        properties.put("bootstrap.servers", bootstrap)
        properties.put(
          "key.deserializer",
          "org.apache.kafka.common.serialization.StringDeserializer"
        )
        properties.put(
          "value.deserializer",
          "org.apache.kafka.common.serialization.StringDeserializer"
        )
        properties.put("group.id", topic)
        properties.put("enable.auto.commit", "true")
        properties.put("auto.commit.interval.ms", "1000")
        properties.put("auto.offset.reset", "earliest")

        val kafkaConsumer = new KafkaConsumer(properties)
        // TODO subscribe to multi topics here
        val topics = List(topic)
        var subscribed = true

        try {
          kafkaConsumer.subscribe(topics.asJava)
          var json_seq = new ListBuffer[String]()
          while (subscribed) {
            val records = kafkaConsumer.poll(100)
            for (record <- records.asScala) {
              var record_string: String = record.value()
              var jsonD: JsValue = Json.parse(record_string)
              json_seq += record_string

            }

            if (json_seq.nonEmpty && json_seq.size > 100) {
              // After accumulating records then append to postgres table
              OutputFunctions.log("Writing to postgres. . . ")
              val json_ds = json_seq.toDS()
              var df = spark.read.json(json_ds)

              // convert time column to timestamp
              // TODO create regex case match for actual actual date field
              df = df
                .withColumn(
                  "time",
                  to_timestamp(col("time"), "yyyy-MM-dd HH:mm:ss")
                )
                .drop("datetime")

              // create table if not exists
              df.createOrReplaceTempView(topic)
              val df2 = spark.sql(s"select * from $topic")
              val prop = new Properties()
              prop.put("driver", "org.postgresql.Driver")
              prop.put("user", user)
              prop.put("password", pass)

              df2.write
                .mode("append")
                .jdbc(s"jdbc:postgresql://$db_host/$db", topic, prop)

              df.write
                .format("jdbc")
                .option("url", s"jdbc:postgresql://$db_host/$db")
                .option("dbtable", topic)
                .option("user", user)
                .option("password", pass)
                .mode("append")
                .save()

              // clear list before leaving
              json_seq = new ListBuffer[String]()

              // if multiple topics break while loop, so move onto next topic.
              if (break) subscribed = true
            }

          }
        } catch {
          case e: Exception => e.printStackTrace()
        } finally {
          kafkaConsumer.close()
        }
      } // end psql consumer

      if (topics == "") {
        // if single topic
        consumer(topic)
      } else {
        // if multiple topics
        var topics_arr: Array[String] = topics.split(",")
        while (true) {
          topics_arr.foreach(topic => consumer(topic, break = true))
          Thread.sleep(2000)
        }
      }
    }
  }

}
//PSQL main
object PSQLConsumer extends App {
  var user: String = ""
  var pass: String = ""
  var db: String = ""
  var db_host: String = ""
  var kafka_host: String = ""
  var topic: String  = ""
  var topics: String = ""

  if (args.isEmpty) {
    println(NO_ARG_INFO)
    println(HELPTEXT)
    sys.exit
  }

  // TODO option for spark cluster manager, spark_ip, spark_port, spark_processors, AND array_buffer chunk size.
  args.foreach(arg => {
    if (arg.matches("--help")) { println(HELPTEXT); sys.exit }
    else if (arg.matches("--db-host=.+")) db_host = arg.split("=")(1)
    else if (arg.matches("--bootstrap-server=.+"))
      kafka_host = arg.split("=")(1)
    else if (arg.matches("--db=.+")) db = arg.split("=")(1)
    else if (arg.matches("--user=.+")) user = arg.split("=")(1)
    else if (arg.matches("--pass=.+")) pass = arg.split("=")(1)
    else if (arg.matches("--topic=.+")) topic = arg.split("=")(1)
    else if (arg.matches("--topics=.+")) topics = arg.split("=")(1)
    else println(s"Invalid argument. $arg")
  })

  if (db_host == "") db_host = "localhost:5432"
  if (kafka_host == "") kafka_host = "localhost:9092"

  if (topic != "") {
    // Single topic
    SparkKafkaConsumer.writeTo.psql(
      bootstrap = kafka_host,
      db_host = db_host,
      db = db,
      user = user,
      pass = pass,
      topic = topic
    )
  } else {
    // Multiple topics
    SparkKafkaConsumer.writeTo.psql(
      bootstrap = kafka_host,
      db_host = db_host,
      db = db,
      user = user,
      pass = pass,
      topics = topics
    )

  }
}

// Other main funcs
object SparkKafkaConsumerMain {
  OutputFunctions.log("APP START")
  OutputFunctions.log("CONSUMER START")
  SparkKafkaConsumer.writeTo.psql(
    bootstrap = "localhost:9092",
    db_host = "loclahost:5432",
    db = "kafka",
    user = "user",
    pass = "password",
    topic = "generatorTest"
  )
}
