package munchkin.steam

import cats.implicits._
import munchkin.steam.model._

import scala.io.Source

trait TestDataFixture {

  lazy val getOwnedGamesRequest = GetOwnedGamesRequest(
    steamid = "steam123",
    include_appinfo = true.some,
    include_played_free_games = true.some,
    appids_filter = List(10, 20, 30, 40)
  )

  lazy val getOwnedGamesResponseRaw =
    Source
      .fromResource("GetOwnedGamesResponse.json")
      .getLines
      .mkString("\n")

  lazy val getOwnedGamesResponse = GetOwnedGamesResponse(
    response = GetOwnedGamesResponseData(
      game_count = 7,
      games = List(
        Game(
          appid = 10,
          name = "Counter-Strike".some,
          img_icon_url = "6b0312cda02f5f777efa2f3318c307ff9acafbb5".some,
          img_logo_url = "af890f848dd606ac2fd4415de3c3f5e7a66fcb9f".some,
          playtime_forever = 32
        ),
        Game(
          appid = 20,
          name = "Team Fortress Classic".some,
          img_icon_url = "38ea7ebe3c1abbbbf4eabdbef174c41a972102b9".some,
          img_logo_url = "515bc393c861d91b9165f0697040c015f50bcb5e".some,
          playtime_forever = 3
        ),
        Game(
          appid = 30,
          name = "Day of Defeat".some,
          img_icon_url = "aadc0ce51ff6ba2042d633f8ec033b0de62091d0".some,
          img_logo_url = "beff21c4d29d2579e794c930bae599cd0c8a8f17".some,
          playtime_forever = 6
        ),
        Game(
          appid = 40,
          name = "Deathmatch Classic".some,
          img_icon_url = "c525f76c8bc7353db4fd74b128c4ae2028426c2a".some,
          img_logo_url = "4bb69695ef9d0ae73e73488fb6456aa4ea1215fa".some,
          playtime_forever = 0
        )
      )
    )
  )

  lazy val getAppDetailsRequest = GetAppDetailsRequest(
    appids = List(30),
    filters = List("name", "platforms", "price_overview")
  )

  lazy val getAppDetailsResponseRaw =
    Source
      .fromResource("GetAppDetailsResponse.json")
      .getLines
      .mkString("\n")

  lazy val getAppDetailsResponse = Map(
    "30" -> AppDetails(
      success = true,
      data = AppDetailsData(
        `type` = "game",
        name = "Day of Defeat",
        steam_appid = 30,
        required_age = 0,
        is_free = false,
        detailed_description =
          "Enlist in an intense brand of Axis vs. Allied teamplay set in the WWII European Theatre of Operations. Players assume the role of light/assault/heavy infantry, sniper or machine-gunner class, each with a unique arsenal of historical weaponry at their disposal. Missions are based on key historical operations. And, as war rages, players must work together with their squad to accomplish a variety of mission-specific objectives.",
        about_the_game =
          "Enlist in an intense brand of Axis vs. Allied teamplay set in the WWII European Theatre of Operations. Players assume the role of light/assault/heavy infantry, sniper or machine-gunner class, each with a unique arsenal of historical weaponry at their disposal. Missions are based on key historical operations. And, as war rages, players must work together with their squad to accomplish a variety of mission-specific objectives.",
        short_description =
          "Enlist in an intense brand of Axis vs. Allied teamplay set in the WWII European Theatre of Operations. Players assume the role of light/assault/heavy infantry, sniper or machine-gunner class, each with a unique arsenal of historical weaponry at their disposal. Missions are based on key historical operations.",
        supported_languages = "English, French, German, Italian, Spanish - Spain",
        header_image = "https://steamcdn-a.akamaihd.net/steam/apps/30/header.jpg?t=1512413490",
        developers = List("Valve").some,
        publishers = List("Valve"),
        price_overview = PriceOverview(
          currency = "USD",
          initial = 499,
          `final` = 499
        ).some,
        platforms = Platforms(
          windows = true,
          mac = true,
          linux = true
        ),
        metacritic = Metacritic(
          score = 79,
          url = "https://www.metacritic.com/game/pc/day-of-defeat?ftag=MCD-06-10aaa1f"
        ).some,
        categories = List(
          Category(
            `id` = 1,
            description = "Multi-player"
          ),
          Category(
            `id` = 8,
            description = "Valve Anti-Cheat enabled"
          )
        ).some,
        genres = List(
          Genre(
            `id` = "1",
            description = "Action"
          )
        ).some,
        screenshots = List(
          Screenshot(
            `id` = 0,
            path_thumbnail = "https://steamcdn-a.akamaihd.net/steam/apps/30/0000000169.600x338.jpg?t=1512413490",
            path_full = "https://steamcdn-a.akamaihd.net/steam/apps/30/0000000169.1920x1080.jpg?t=1512413490"
          ),
          Screenshot(
            `id` = 1,
            path_thumbnail = "https://steamcdn-a.akamaihd.net/steam/apps/30/0000000170.600x338.jpg?t=1512413490",
            path_full = "https://steamcdn-a.akamaihd.net/steam/apps/30/0000000170.1920x1080.jpg?t=1512413490"
          )
        ).some,
        movies = None,
        recommendations = Recommendations(
          total = 2791
        ).some,
        release_date = ReleaseDate(
          coming_soon = false,
          date = "May 1, 2003"
        )
      )
    )
  )
}
