# Getting started  
## Base setup  
1.  Setup Scala 2.11.x
2.  Setup SBT (Pref 1.6.2)
3.  Clone this repository
4.  sbt assembly

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


