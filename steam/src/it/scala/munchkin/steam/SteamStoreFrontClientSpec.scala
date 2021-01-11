package munchkin.steam

import cats.effect.{Blocker, IO}
import io.circe.parser
import munchkin.steam.model._
import org.http4s.client.JavaNetClientBuilder
import org.http4s.client.middleware.FollowRedirect
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.ExecutionContext

class SteamStoreFrontClientSpec extends AnyWordSpec with Matchers with TestDataFixture {
  "SteamStoreFrontClient" should {
    implicit val cs = IO.contextShift(ExecutionContext.global)
    val blocker = Blocker.liftExecutionContext(ExecutionContext.global)
    val client = FollowRedirect(5)(JavaNetClientBuilder[IO](blocker).create)
    "getAppDetails" in {
      val program = for {
        json <- IO.fromEither(parser.parse(getAppDetailsResponseRaw))
        expected <- IO.fromEither(json.as[GetAppDetailsResponse])
        result <- SteamStoreFrontClient
          .build[IO](client)
          .getAppDetails(getAppDetailsRequest)
      } yield result must equal(expected)

      program.unsafeRunSync
    }
  }
}
