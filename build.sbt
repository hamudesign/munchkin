import design.hamu.Dependencies
import design.hamu.CompilerPlugins

lazy val commonSettings = Seq(
  scalaVersion := "2.13.2",
  organization := "design.hamu",
  version := "0.0.1",
  scalacOptions := Seq("-Xlint", "-Ywarn-unused", "-deprecation", "-Ymacro-annotations"),
  dependencyUpdatesFailBuild := true
)

lazy val publishSettings = Seq(
  coverageMinimum := 90,
  coverageFailOnMinimum := true,
  crossScalaVersions := Seq(
    "2.12.10",
    "2.13.2"
  ),
  scalacOptions := {
    scalaBinaryVersion.value match {
      case v if v.startsWith("2.12") => Seq("-Ypartial-unification", "-deprecation")
      case v if v.startsWith("2.13") => Seq("-Ymacro-annotations", "-Xlint", "-Ywarn-unused", "-deprecation")
      case _ => Seq()
    }
  },
  libraryDependencies ++= {
    scalaBinaryVersion.value match {
      case v if v.startsWith("2.13") =>
        Seq()
      case _ =>
        Seq(
          compilerPlugin(CompilerPlugins.MacroParadise.core cross CrossVersion.full)
        )
    }
  },
  homepage := Some(url("https://hamuhouse.github.io/munchkin/")),
  licenses := List("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  developers := List(
    Developer(id = "matsudaskai", name = "Kai Matsuda", email = "", url = url("https://vangogh500.github.io/"))
  ),
  scmInfo := Some(
    ScmInfo(url("https://github.com/hamuhouse/munchkin"), "scm:git@github.com:hamuhouse/munchkin.git")
  ),
  publishTo := sonatypePublishTo.value
)

lazy val root = project
  .in(file("."))
  .aggregate(
    steam
  )

lazy val steam = project
  .in(file("steam"))
  .configs(IntegrationTest)
  .settings(
    name := "munchkin-steam",
    commonSettings,
    publishSettings,
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      Dependencies.Http4s.client,
      Dependencies.Http4s.circe,
      Dependencies.Circe.generic
    ) ++ Seq(
      Dependencies.Circe.parser,
      Dependencies.Scalatest.core,
      Dependencies.ScalaMock.core
    ).map(_ % "it,test"),
    addCompilerPlugin(CompilerPlugins.KindProjector.core cross CrossVersion.full)
  )

lazy val docs = project
  .in(file("docs"))
  .settings(
    name := "munchkin-docs",
    micrositeName := "Munchkin",
    micrositeCompilingDocsTool := WithTut,
    micrositeBaseUrl := "munchkin",
    micrositeHomepage := "https://hamudesign.github.io/munchkin/",
    micrositeHighlightTheme := "atom-one-light",
    git.remoteRepo := "https://github.com/hamudesign/munchkin.git"
  )
  .enablePlugins(MicrositesPlugin)

