import Dependencies._

ThisBuild / scalaVersion     := "2.11.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.goldteam"
ThisBuild / organizationName := "goldteam"


lazy val spark = Seq(
  "org.apache.spark" %% "spark-core" % "2.4.8",
  "org.apache.spark" %% "spark-hive" % "2.4.8",
  "org.apache.spark" %% "spark-hive" % "2.4.8",
  "org.apache.spark" %% "spark-sql" % "2.4.8",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.8",
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.8",
  "org.apache.spark" %% "spark-streaming" % "2.4.8" % "provided"

)

lazy val jdbc = Seq(
    "org.postgresql" % "postgresql" % "42.3.3"
)
lazy val vega = Seq(
    //"org.openjfx" % "javafx-swing" % "17",
    //"org.openjfx" % "javafx-web" % "17",
    "org.vegas-viz" %% "vegas" % "0.3.11",
    "org.vegas-viz" %% "vegas-spark" % "0.3.11"
)
lazy val kafka = Seq(
    "kafka-client" % "kafka-client" % "2.4.1" from "https://repo1.maven.org/maven2/org/apache/kafka/kafka-clients/2.4.1/kafka-clients-2.4.1.pom",
    "org.apache.kafka" %% "kafka" % "2.4.1"
)
lazy val play = Seq(
    "com.typesafe.play" %% "play-json" % "2.7.4"
)


assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
}

lazy val root = (project in file("."))
  .settings(
    name := "lab",

    assembly / mainClass := Some("org.goldteam.kafka.consumer.SparkKafkaConsumerMain"),
    assembly / assemblyJarName := "assembledJar.jar",


    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.10",
    libraryDependencies ++= play,
    libraryDependencies ++= spark,
    libraryDependencies ++= vega,
    libraryDependencies ++= kafka,
    libraryDependencies ++= jdbc



  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
