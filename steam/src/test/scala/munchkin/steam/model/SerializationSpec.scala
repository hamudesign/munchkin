package munchkin.steam.model

import io.circe.syntax._
import io.circe.parser
import munchkin.steam.TestDataFixture
import org.scalatest.{Inside, Inspectors}
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SerializationSpec
    extends AnyWordSpec
    with Matchers
    with Inside
    with Inspectors
    with JsonInspectors
    with TestDataFixture {
  "Serialization" should {
    "deserialize GetAppDetails" in {
      val program = for {
        json <- parser.parse(getAppDetailsResponseRaw)
        deserialized <- json.as[GetAppDetailsResponse]
      } yield deserialized must equal(getAppDetailsResponse)

      program.fold(e => fail(e), a => a)
    }
    "serialize GetAppDetails" in {
      val program = for {
        json <- parser.parse(getAppDetailsResponseRaw)
      } yield assertJsonSubset(getAppDetailsResponse.asJson, json)

      program.fold(e => fail(e), a => a)
    }
    "deserialize GetOwnedGames" in {
      val program = for {
        json <- parser.parse(getOwnedGamesResponseRaw)
        deserialized <- json.as[GetOwnedGamesResponse]
      } yield deserialized must equal(getOwnedGamesResponse)

      program.fold(e => fail(e), a => a)
    }
    "serialize GetOwnedGames" in {
      val program = for {
        json <- parser.parse(getOwnedGamesResponseRaw)
      } yield assertJsonSubset(getOwnedGamesResponse.asJson, json)

      program.fold(e => fail(e), a => a)
    }
  }
}
