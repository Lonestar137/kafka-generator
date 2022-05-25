# Getting started  
This tutorial assumes that you have already configured Kafka, and created a Kafka topic.  Also, that you have downloaded PSQL and enabled md5 password authentication.
If you have not, please refer to these resources:  
[Postgresql Install](https://www.geeksforgeeks.org/install-postgresql-on-linux/)  
[PostgreSQL Auth Configuration](https://www.liquidweb.com/kb/change-postgresql-authentication-method-from-ident-to-md5/)  
[CentOs Kafka Install](https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-centos-7)  
Or, for Ubuntu users:
[Ubuntu Kafka Install](https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-ubuntu-20-04)  

Alternatively, you may install and configure Kafka and Zookeeper using a Docker container (recommended):

[Docker Kafka Install](https://towardsdatascience.com/how-to-install-apache-kafka-using-docker-the-easy-way-4ceb00817d8b)

## Base setup  
1. Setup Scala 2.11.x
```
# install sdkman.io

curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# install latest version of scala 2.11 via sdkman

sdk install scala 2.11.12
```


2. Setup SBT (Pref 1.6.2)

`sdk install sbt`

3. Clone this repository

`git clone https://github.com/Lonestar137/kafka-generator.git`

4. sbt assembly
5. Edit psqlconsumer.service, define your variables on the EXECSTART line.

## Kafka Consumer w/ PSQL 
1.  Edit psqlconsumer.service  EXECSTART,  put your psql credentials.  If using a remote host or non-default port for kafka/psql, then you need to set that as well.
2.  Copy the psqlconsumer.service to /etc/systemd/system/
3.  Run sudo systemctl daemon-reload 
4.  sudo systemctl start psqlconsumer.service
5.  sudo systemctl status psqlconsumer.service to verify it's running.
6.  (Optional) To troubleshoot the service, run:  sudo journalctl -u psqlconsumer.service
7.  Verify database. (Connect to your database and see if tables are generated.)

## Kafka Producer  
1. sbt run 
2. At the prompt, select KafkaProducerMain.


