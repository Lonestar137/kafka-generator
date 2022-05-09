package org.goldteam.kafka.consumer;

import java.util.Calendar
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

//TODO spark deps

//json
import play.api.libs.json._


//custom classes
import logging.OutputFunctions._



object SparkKafkaConsumer{
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
                    println("SET:"+jsonD)
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

    log("APP START")
    log("CONSUMER START")
    consumer("generatorTest")

}

object SparkKafkaConsumerMain extends App {
    log("APP START")
    log("CONSUMER START")
    SparkKafkaConsumer.consumer("generatorTest")
}
