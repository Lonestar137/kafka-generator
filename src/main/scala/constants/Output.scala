package constants

object Output {

  val HELPTEXT: String = """
Usage: scala SparkKafkaConsumer.jar --db-host=[psql_host:port] --bootstrap-server=[kafka-bootstrap:port] ...

Database credentials and servers must be defined for this program to operate correctly. Use the options below to do so.

Required:
  Options:
  --db-host           The host server:port that contains the db to write data to.
  --db                The db to write the data to.
  --bootstrap-server  The kafka server to read from.
  --user              Database user to access tables.
  --pass              Database password to access tables.

  Choose One Option:
  --topic             Kafka topic to read from.
  --topics            Kafka topics to read from, if you select this option don't use the other topic option. List comma separated topics.

Other Options:
  --spark-master      Not implemented.
"""

  val NO_ARG_INFO: String = "No arguments passed please refer to the below."

}
