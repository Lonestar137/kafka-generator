package org.goldteam.kafka.producer;

import java.util.Date
import scala.util.Random
import java.util.Calendar
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

//json
import play.api.libs.json._

//random
import scala.util.Random

//import GeneratorObjects._
import org.goldteam.generator.Generator
import logging.OutputFunctions._

object GoldteamProducer {
  def producer(node: String, topic: String = "test", value: String): Unit = {
    val props: Properties = new Properties()
    props.put("bootstrap.servers", node)
    props.put(
      "key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer"
    )
    props.put(
      "value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer"
    )
    props.put("acks", "all")

    val producer = new KafkaProducer[String, String](props)

    try {
      val record = new ProducerRecord[String, String](topic, value)
      producer.send(record)

      println("Record sent")
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      producer.close()
    }

  }

}

object KafkaProducerMain extends App {
  log("PRODUCER MAIN START")

  var topic = ""
  var topics = ""
  var kafka_host = ""

  var helpText =
    """Usage:  run --bootstrap-server=[ip]:[port] --topic=[topic_name]
Options:
  --bootstrap-server  Server hosting your  kafka instance.
  --topic             Defines the kafka topic you want to subscribe to.
  --topics            Not yet implemented.
"""

  if (args.isEmpty) {
    println("No arguments passed, you can define other options like below.")
    println(helpText)
  }

  // TODO option for spark cluster manager, spark_ip, spark_port, spark_processors, AND array_buffer chunk size.
  args.foreach(arg => {
    if (arg.matches("--help")) { println(helpText); sys.exit }
    else if (arg.matches("--bootstrap-server=.+"))
      kafka_host = arg.split("=")(1)
    else if (arg.matches("--topic=.+")) topic = arg.split("=")(1)
    else if (arg.matches("--topics=.+")) topics = arg.split("=")(1)
    else println(s"Invalid argument. $arg")
  })

  if (kafka_host == "") kafka_host = "0.0.0.0:9092"
  if (topic == "") topic = "generatorTest"

  // loc, topic, value
  var run = true
  var sleep_time =
    scala.util.Random.nextInt(30) * 10 // wait time between messages 10-300 ms
  Generator.initializeGenerator();

  // Generator.testRun();
  while (run) {
    var value = Generator.generateOrderJSON()
    GoldteamProducer.producer(kafka_host, topic, value)
    Thread.sleep(sleep_time) // sleep for 1 second
    sleep_time = scala.util.Random.nextInt(30) * 10
  }

}
