import java.util.Calendar

import org.goldteam.kafka.producer.GoldteamProducer
import org.goldteam.kafka.consumer.SparkKafkaConsumer




object Main extends App{

    def log(value : String) : Unit = {
      println("[" + Calendar.getInstance().getTime().toString() + "] " + value);
    }

    def readCSV(fileName: String): List[String] = {
      val bufferedSource = scala.io.Source.fromFile(fileName)
      val lines = bufferedSource.getLines.toList
      bufferedSource.close
      lines
    }

    log("APP START")

    log("PRODUCER START")
    for(i <- 1 to 10){
        GoldteamProducer.producer("localhost:9092", "generatorTest", "{\"order_id\": "+i+", \"customer_id\": 1001}")
    }
    log("PRODUCER END")

    log("CONSUMER START")
    SparkKafkaConsumer.consumer("generatorTest")

    log("APP END")

}
