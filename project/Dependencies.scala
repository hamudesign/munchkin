package design.hamu

import sbt._

object Dependencies {

  object ScalaLang {
    def compiler(scalaVersion: String) = "org.scala-lang" % "scala-compiler" % scalaVersion
  }

  object Cats {
    private val version = "2.1.1"
    val core = "org.typelevel" %% "cats-core" % version
    val effect = "org.typelevel" %% "cats-effect" % version
  }

  object Http4s {
    private val version = "0.21.15"
    val client = "org.http4s" %% "http4s-client" % version
    val circe = "org.http4s" %% "http4s-circe" % version
  }

  object SLF4J {
    private val version = "1.7.30"
    val api = "org.slf4j" % "slf4j-api" % version
  }

  object Logback {
    private val version = "1.2.3"
    val classic = "ch.qos.logback" %  "logback-classic" % version
  }

  object Logstash {
    private val version = "6.1"
    val encoder = "net.logstash.logback" % "logstash-logback-encoder" % "6.1"
  }

  object Jackson {
    private val version = "2.10.5"
    val core = "com.fasterxml.jackson.core" % "jackson-core" % version
  }

  object Circe {
    private val version = "0.13.0"
    val core =  "io.circe" %% "circe-core" % version
    val generic = "io.circe" %% "circe-generic" % version
    val parser = "io.circe" %% "circe-parser" % version
  }

  object Scalatest {
    private val version = "3.2.0"
    val core: ModuleID = "org.scalatest" %% "scalatest" % version
  }

  object ScalaMock {
    val core: ModuleID = "org.scalamock" %% "scalamock" % "5.1.0"
  }
}
