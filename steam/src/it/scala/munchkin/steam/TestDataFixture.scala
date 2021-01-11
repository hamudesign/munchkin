package munchkin.steam

import cats.implicits._
import munchkin.steam.model._

import scala.io.Source

trait TestDataFixture {

  lazy val getAppDetailsRequest = GetAppDetailsRequest(
    appids = List(30)
  )

  lazy val getAppDetailsResponseRaw =
    Source
      .fromResource("GetAppDetailsResponse.json")
      .getLines
      .mkString("\n")

  lazy val getOwnedGamesRequest = GetOwnedGamesRequest(
    steamid = "76561197960434622",
    include_appinfo = true.some,
    appids_filter = List(10,20,30,40,50,60,70)
  )

  lazy val getOwnedGamesResponseRaw =
    Source
      .fromResource("GetOwnedGamesResponse.json")
      .getLines
      .mkString("\n")

}
