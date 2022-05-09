package org.goldteam.kafka.producer;

import java.util.Calendar
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

//json
import play.api.libs.json._

import GeneratorObjects._
import logging.OutputFunctions._


object GoldteamProducer{
    def producer(node: String, topic: String = "test", value: String): Unit = {
        val props: Properties = new Properties()
        props.put("bootstrap.servers", node)
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("acks", "all")

        val producer = new KafkaProducer[String, String](props)

        try{
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
  //loc, topic, value
  GoldteamProducer.producer("localhost:9092", "test", "test")
  

}
