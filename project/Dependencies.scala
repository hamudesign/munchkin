package design.hamu

import sbt._

object Dependencies {

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

  object Circe {
    private val version = "0.13.0"
    val core =  "io.circe" %% "circe-core" % version
    val generic = "io.circe" %% "circe-generic" % version
    val parser = "io.circe" %% "circe-parser" % version
  }

  object Scalatest {
    private val version = "3.2.3"
    val core: ModuleID = "org.scalatest" %% "scalatest" % version
  }

  object ScalaMock {
    val core: ModuleID = "org.scalamock" %% "scalamock" % "5.1.0"
  }
}
