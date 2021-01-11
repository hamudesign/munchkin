package munchkin.steam

import java.net.URLDecoder

import io.circe.parser
import cats.effect.{IO, Resource}
import munchkin.steam.model._
import org.http4s.Response
import org.http4s.circe.CirceEntityEncoder
import org.http4s.client.Client
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SteamEdgeClientSpec extends AnyWordSpec with Matchers with TestDataFixture with CirceEntityEncoder {
  "SteamEdgeClient" should {
    val apiKey = "dummyapikey"
    "getOwnedGames" in {

      val client = Client[IO] { req =>
        Resource.liftF[IO, Response[IO]] {
          if (req.uri.params("key") == apiKey) {
            if (req.uri.params("format") == "json") {
              for {
                raw <- IO(req.uri.params("input_json"))
                decoded <- IO(URLDecoder.decode(raw, "UTF-8"))
                json <- IO.fromEither(parser.parse(decoded))
                input <- IO.fromEither(json.as[GetOwnedGamesRequest])
                result <- if (input == getOwnedGamesRequest) IO.pure(Response[IO]().withEntity(getOwnedGamesResponse))
                else IO.raiseError(new Exception("Invalid input"))
              } yield result
            } else IO.raiseError(new Exception("Invalid format"))
          } else IO.raiseError(new Exception("Invalid apikey"))
        }
      }

      val program = for {
        response <- SteamEdgeClient
          .build[IO](apiKey, client)
          .getOwnedGames(getOwnedGamesRequest)
      } yield response must equal(getOwnedGamesResponse)

      program.unsafeRunSync
    }
  }
}
