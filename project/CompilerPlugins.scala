package design.hamu

import sbt._

object CompilerPlugins {

  object KindProjector {
    val core: ModuleID = "org.typelevel" % "kind-projector" % "0.11.0"
  }

  object MacroParadise {
    val core: ModuleID = "org.scalamacros" % "paradise" % "2.1.1"
  }
}
