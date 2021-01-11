package munchkin.steam

import cats.effect.{IO, Resource}
import org.http4s.Response
import org.http4s.circe.CirceEntityEncoder
import org.http4s.client.Client
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SteamStoreFrontClientSpec extends AnyWordSpec with Matchers with TestDataFixture with CirceEntityEncoder {
  "SteamStoreFrontClient" should {
    "getAppDetails" in {
      val client = Client[IO] { req =>
        Resource.liftF[IO, Response[IO]] {
          if (req.uri.params("appids") == getAppDetailsRequest.appids.mkString(",")) {
            if (req.uri.params("filters") == getAppDetailsRequest.filters.mkString(",")) {
              IO.pure(Response[IO]().withEntity(getAppDetailsResponse))
            } else IO.raiseError(new Exception("Invalid filters"))
          } else IO.raiseError(new Exception("Invalid appids"))
        }
      }

      val program = for {
        response <- SteamStoreFrontClient
          .build[IO](client)
          .getAppDetails(getAppDetailsRequest)
      } yield response must equal(getAppDetailsResponse)

      program.unsafeRunSync
    }
  }
}
