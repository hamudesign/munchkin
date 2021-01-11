package munchkin.steam

import io.circe.parser
import cats.effect.{Blocker, IO}
import munchkin.steam.model.GetOwnedGamesResponse
import org.http4s.client.JavaNetClientBuilder
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.ExecutionContext

class SteamEdgeClientSpec extends AnyWordSpec with Matchers with TestDataFixture {
  "SteamEdgeClient" should {
    implicit val cs = IO.contextShift(ExecutionContext.global)
    val blocker = Blocker.liftExecutionContext(ExecutionContext.global)
    val client = JavaNetClientBuilder[IO](blocker).create
    val apiKey = sys.env("STEAM_API_KEY")
    "getOwnedGames" in {
      val program = for {
        json <- IO.fromEither(parser.parse(getOwnedGamesResponseRaw))
        expected <- IO.fromEither(json.as[GetOwnedGamesResponse])
        result <- SteamEdgeClient
          .build[IO](apiKey, client)
          .getOwnedGames(getOwnedGamesRequest)
      } yield result mustBe a [GetOwnedGamesResponse]

      program.unsafeRunSync
    }
  }
}
