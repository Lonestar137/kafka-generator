## Requirements  
* Scala 2.11.x
* SBT (Pref 1.6.2)
* A working Kafka instance
* PostgreSQL /w md5 password auth enabled.
* A Linux OS

## Installation

For instructions on setup, refer to the STARTUP.md

## PSQLConsumer  
The PSQL consumer is a consumer main function which reads from a kafka topic(s) of your choosing and writes the data to a user-defined psql db.  The table is automatically generated 
and appended to by the consumer.  Consumer created tables will share the same name as the topic that they were created from.

### Defining the configuration  
There are numerous commandline variables that can be set by the user on startup to change the configuration of the consumer.

To see the available commands you can always run:  scala target/scala_{version}/PSQLConsumer_{version}.jar --help
OR, run sbt from the project root dir(dir w/ build.sbt):
```
$ sbt
> run --help
Available options:  
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
```

## KafkaProducer  
The KafkaProducer is a sample data generator.  It is not necessary for projects that already have a topic receiving data generation.

The KafkaProducer can generator data to a topic of your choosing, where you can then consume the data with the PSQLConsumer main function to a psql database of your choosing.






