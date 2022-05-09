import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import scala.collection.JavaConverters._

//json
import play.api.libs.json._

//import org.goldteam.generator._
import GeneratorObjects._


trait OutputFunctions {

  def printRed(str: String, newLine: Boolean = true) = {
    if (newLine) {
      Console.println("\u001b[31m" + str + "\u001b[0m")
    } else {
      Console.print("\u001b[31m" + str + "\u001b[0m")
    }
  }
  def printYellow(str: String, newLine: Boolean = true) = {
    if (newLine){
      Console.println("\u001b[33m" + str + "\u001b[0m")
    } else {
      Console.print("\u001b[33m" + str + "\u001b[0m")
    }
  }
  def printGreen(str: String, newLine: Boolean = true) = {
    if (newLine){
      Console.println("\u001b[32m" + str + "\u001b[0m")
    } else {
      Console.print("\u001b[33m" + str + "\u001b[0m")
    }
  }

}


object Main extends App{

    def readCSV(fileName: String): List[String] = {
      val bufferedSource = scala.io.Source.fromFile(fileName)
      val lines = bufferedSource.getLines.toList
      bufferedSource.close
      lines
    }

    def producer(topic: String = "test", value: String): Unit = {
        val props: Properties = new Properties()
        props.put("bootstrap.servers", "3.94.111.218:9092")
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("acks", "all")

        val producer = new KafkaProducer[String, String](props)

        try{
            val record = new ProducerRecord[String, String](topic, value)
            producer.send(record)
            
            //println(s"sent record $key $value")
            println("sent record")
        } catch {
            case e: Exception => e.printStackTrace()
        } finally {
            producer.close()
        }

    }
//
    def consumer(topic: String="test"): Unit={
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
        implicit val formats = org.json4s.DefaultFormats
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
                    val jsonD2: JsObject = jsonD.as[JsObject]
                    println("Key():"+jsonD("order_id"))
                    println("SET:"+jsonD)
                    //println("Keyjsobj:"+jsonD2("order_id"))
                    //println("Key--:"+jsonD \\ "order_id")

                }
            }
        } catch {
            case e: Exception => e.printStackTrace()
        } finally {
            kafkaConsumer.close()
        }
    }

    //val countries_csv = readCSV("/home/jonesgc/Documents/countries.csv")
    //countries_csv.foreach(v => producer(v, "test"))

    //for(i <- 1 to 10){
    //    producer("generatorTest", "{\"order_id\": "+i+", \"customer_id\": 1001}")
    //}

    //consumer("generatorTest")

    var ordr = new Order
    ordr.order_id = 1
    println(ordr.order_id)
}
