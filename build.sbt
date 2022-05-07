import Dependencies._

ThisBuild / scalaVersion     := "2.11.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "lab",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.10",
    libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.1",
    //libraryDependencies += "org.apache.kafka" %% "kafka-client" % "2.4.1",
    libraryDependencies += "kafka-client" % "kafka-client" % "2.4.1"from "https://repo1.maven.org/maven2/org/apache/kafka/kafka-clients/2.4.1/kafka-clients-2.4.1.pom",
    libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.5",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.4"



  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
