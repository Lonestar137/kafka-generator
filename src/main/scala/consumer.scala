package org.goldteam.kafka.consumer;

import java.util.Calendar
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import scala.collection.mutable.ListBuffer

//spark
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType, StructField}
//import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent

//drivers
import java.sql.DriverManager
import java.sql.Connection

//json
import play.api.libs.json._


//custom classes
import logging.OutputFunctions



object SparkKafkaConsumer{
  val spark = SparkSession.builder.master("local[*]").appName("SparkKafkaConsumer").getOrCreate()
  import spark.implicits._

  def consumer(topic: String="test"): Unit = {
      val properties = new Properties()
      properties.put("bootstrap.servers", "localhost:9092")
      properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
      properties.put("group.id", topic)
      properties.put("enable.auto.commit", "true")
      properties.put("auto.commit.interval.ms", "1000")
      properties.put("auto.offset.reset", "earliest")

      val kafkaConsumer = new KafkaConsumer(properties)
      val topics = List(topic)
      var  subscribed = true

      try{
          //Note: Redundant subscription, not necessary, leaving for example purposes.
          kafkaConsumer.subscribe(topics.asJava)
          while(subscribed){
              val records = kafkaConsumer.poll(100)
              for(record <- records.asScala){
                  val record_string: String = record.value()
                  //println(s"offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}")
                  //val jsonD = parse(record.value()).extract[Map[String, Any]]
                  val jsonD: JsValue = Json.parse(record_string)
                  println("Key():"+jsonD("order_id"))
                  println(jsonD)
                  //println("Keyjsobj:"+jsonD2("order_id"))
                  //println("Key--:"+jsonD \\ "order_id")
                  //println("Keyjsobj:"+jsonD.as[JsObject]("order_id"))
                  //val jsonD2: JsObject = jsonD.as[JsObject]
                  // println("Keyjsobj:"+jsonD2("order_id"))
                  // println("Key--:"+jsonD \\ "order_id")

              }
          }
      } catch {
          case e: Exception => e.printStackTrace()
      } finally {
          kafkaConsumer.close()
      }
  }

  object writeTo{
      //specific implementations of consumer
      def psql(db: String, user: String, pass: String, topic: String): Unit = {
          import spark.sqlContext.implicits._

        //TODO read data to postgres from kafka using spark
        def consumer(topic: String="test"): Unit ={
              val properties = new Properties()

              properties.put("bootstrap.servers", "localhost:9092")
              properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
              properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
              properties.put("group.id", topic)
              properties.put("enable.auto.commit", "true")
              properties.put("auto.commit.interval.ms", "1000")
              properties.put("auto.offset.reset", "earliest")

              val kafkaConsumer = new KafkaConsumer(properties)
              val topics = List(topic)
              var  subscribed = true

              try{
                  //Note: Redundant subscription, not necessary, leaving for example purposes.
                  kafkaConsumer.subscribe(topics.asJava)
                  var json_seq = new ListBuffer[String]()
                  while(subscribed){
                      val records = kafkaConsumer.poll(100)
                      for(record <- records.asScala){
                          var record_string: String = record.value()
                          var jsonD: JsValue = Json.parse(record_string)
                          println("record offset: "+record.offset())
                          println("Key():"+jsonD("order_id"))
                          //convert jsonD("datetime") to format year-month-day hour:minute:second from Mon Jan 19 20:57:29 CST 1970
                          //val date_time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(record.timestamp()))
                          //val date_time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(jsonD("datetime")))
                          //record_string = jsonD.toString()

                          //TODO create a regex case match to change Alex date formatting here to valid postgres date format yyyy-mm-dd hh:mm:ss
                          //get the string from jsonD("datetime") and convert to unixtime.


                          println("condition: "+(record.offset() == records.count()-1) + "offset, count: "+record.offset() + " " + records.count())
                          json_seq += record_string

                      }

                      if(json_seq.nonEmpty && json_seq.size > 100){
                          //After accumulating records then append to postgres table
                          println("Writing to postgres")
                          val json_ds = json_seq.toDS()
                          var df = spark.read.json(json_ds)

                          //convert time column to timestamp
                          //TODO create regex case match for actual actual date field
                          df = df.withColumn("time", to_timestamp(col("time"), "yyyy-MM-dd HH:mm:ss")).drop("datetime")



                          //TODO make the table name the topic name that the data is coming from
                          //create table if not exists
                          df.createOrReplaceTempView("orders")
                          val df2 = spark.sql("select * from orders")
                          val prop = new Properties()
                            prop.put("driver", "org.postgresql.Driver")
                            prop.put("user", user)
                            prop.put("password", pass)
                          df2.write.mode("append").jdbc("jdbc:postgresql://localhost:5432/"+db, "orders", prop)

                          df.write.format("jdbc")
                              .option("url", "jdbc:postgresql://localhost:5432/"+db)
                              .option("dbtable", "orders")
                              .option("user", user)
                              .option("password", pass)
                              .mode("append").save()

                          // clear list before leaving
                          json_seq = new ListBuffer[String]()
                      }

                  }
              } catch {
                  case e: Exception => e.printStackTrace()
              } finally {
                  kafkaConsumer.close()
              }
        } //end psql consumer

        consumer(topic)
      }
  }
 

}

object SparkKafkaConsumerMain extends App {
    OutputFunctions.log("APP START")
    OutputFunctions.log("CONSUMER START")

   // SparkKafkaConsumer.consumer("generatorTest")
    SparkKafkaConsumer.writeTo.psql(db="kafka", user="your_psql_user", pass="your_psql_pass", topic="generatorTest")

    //sample data from generator
    //SparkKafkaConsumer.sparkConsumer("test")
    //{"order_id":99,
    //"customer_id":99,
    //"customer_name":"Gardner Jesse",
    //"product_id":26,"product_name":"S Blue Denim Shirt",
    //"product_category":"Clothing",
    //"payment_type":"Debit",
    //"qty":4,
    //"price":47.49999940395355,
    //"datetime":"Mon Jan 26 00:17:55 CST 1970",
    //"country":"South Korea",
    //"city":"Muan",
    //"ecommerce_website_name":"walmart.com",
    //"payment_txn_id":"99",
    //"payment_txn_success":true,
    //"failure_reason":""}
}
