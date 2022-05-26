# Getting started  
This tutorial assumes that you have already configured Kafka, and created a Kafka topic.  Also, that you have downloaded PSQL and enabled md5 password authentication.
If you have not, please refer to these resources:

[PostgreSQL Install](https://www.geeksforgeeks.org/install-postgresql-on-linux/)  
[PostgreSQL Auth Configuration](https://www.liquidweb.com/kb/change-postgresql-authentication-method-from-ident-to-md5/)  
[CentOs Kafka Install](https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-centos-7)  
Or, for Ubuntu users:
[Ubuntu Kafka Install](https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-ubuntu-20-04)  

Alternatively, you may install and configure Kafka and Zookeeper using a Docker container (recommended):

[Docker Kafka Install](https://towardsdatascience.com/how-to-install-apache-kafka-using-docker-the-easy-way-4ceb00817d8b)

## Base setup  
1. **Setup Scala 2.11.12**

```
# install sdkman.io

curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# install latest version of scala 2.11 via sdkman

sdk install scala 2.11.12
```

2. **Setup SBT (Pref 1.6.2)**

`sdk install sbt 1.6.2`

3. **Clone this repository to your local dev environment:**

`git clone https://github.com/Lonestar137/kafka-generator.git`

4. **sbt assembly**

`sbt assembly`

5. **Edit kafkaproducer.service, define your variables on the EXECSTART line.**

```
ExecStart=/bin/sh -c 'scala /opt/kafka-generator/target/scala-2.11/kafkaproducer-{version}.jar 
```

6. ****For the PSQLProducer service(not required), inside build.sbt make the following changes:**
```
    assembly / mainClass := Some("org.goldteam.kafka.consumer.PSQLConsumer"),
    assembly / assemblyJarName := s"PSQLConsumer-${scalaVersion.value}.jar",


    //assembly / mainClass := Some("org.goldteam.kafka.producer.KafkaProducerMain"),
    //assembly / assemblyJarName := s"KafkaProducer-${scalaVersion.value}.jar",

```
Rerun `sbt assembly`.

```
ExecStart=/bin/sh -c 'scala /opt/kafka-generator/target/scala-2.11/PSQLProducer-{version}.jar --db-host=localhost:5432 --db=your_psql_db --bootstrap-server=localhost:9092 --user=your_user--pass=your_pass --topic=genericTopic'
```

## KafkaProducer
1. Edit KafkaProducer .service  EXECSTART.  

```
ExecStart=/bin/sh -c 'scala /opt/kafka-generator/target/scala-2.11/KafkaProducer-{version}.jar  --bootstrap-server=[kafka_host_ip]:[port] --topic=genericTopic'
```

2. Copy the KafkaProducer.service to /etc/systemd/system/

`sudo cp ./kafkaproducer.service /etc/systemd/system`


3. Run sudo systemctl daemon-reload 

`sudo system ctl daemon-reload`

4. Run sudo systemctl start kafkaproducer.service

`sudo systemctl start psqlconsumer.service`

5. Run sudo systemctl status kafkaproducer.service to verify it's running.

`sudo systemctl status kafkaproducer.service`

6. (Optional) To troubleshoot the service, run:  sudo journalctl -u kafkaproducer.service

`sudo journalctl -u kafkaproducer.service`

7. Verify database. (Connect to your database and see if tables are generated.)

`select * from yourtable limit 10;`


## PSQL Consumer
You will need to define the below options, which can be seen by running the PSQLConsumer file with the `--help` option:
```
Usage: scala SparkKafkaConsumer.jar --db-host=[psql_host:port] --bootstrap-server=[kafka-bootstrap:port] ...

Database credentials and servers must be defined for this program to operate correctly. Use the options below to do so.

Required:
  Options: --db-host           The host server:port that contains the db to write data to.
  --db                The db to write the data to(looks for local db by default).
  --bootstrap-server  The kafka server to read from.
  --user              Database user to access tables.
  --pass              Database password to access tables.

  Choose One Option:
  --topic             Kafka topic to read from.
  --topics            Kafka topics to read from, if you select this option don't use the other topic option. List comma separated topics.

Other Options:
  --spark-master      Not implemented.
```

Example:
1. Running the project.  Inside the root directory of the project(the same level as the build.sbt) do:
```
sbt

> run --db_host=ip_hosting_psql:port --db=your_db  --user=psql_user --pass=psql_pass --bootstrap-server=kafka_server_ip:port --topic=your_kafka_topic
```

2. At the prompt, select PSQLConsumer.


# Helpful commands

### Kafka topic creation, production, consumption:

**Create a Topic:**

`kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic generatorTest`

**Run Producer:**

`echo "Hello, World" | ~/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic generatorTest > /dev/null`

**Run Consumer:**

`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic generatorTest --from-beginning`
