package munchkin.steam.model

import io.circe.{Json, JsonObject}
import org.scalatest.matchers.must.Matchers
import org.scalatest.{Assertion, Inspectors}

trait JsonInspectors { this: Inspectors with Matchers =>

  def assertJsonSubset(a: Json, b: Json): Assertion = (a.asObject, b.asObject) match {
    case (Some(a), Some(b)) => assertJsonSubset(a, b)
    case (a, b) => a must equal(b)
  }

  def assertJsonSubset(a: JsonObject, b: JsonObject): Assertion = {
    forAll(a.keys) { key =>
      (a(key), b(key)) match {
        case (Some(Json.Null), None) => succeed
        case (None, Some(Json.Null)) => succeed
        case (Some(aV), Some(bV)) if aV.isArray && bV.isArray =>
          forAll(aV.asArray.get.zip(bV.asArray.get)) {
            case (aE, bE) if aE.isObject && bE.isObject =>
              assertJsonSubset(aE.asObject.get, bE.asObject.get)
            case (aE, bE) => aE must equal(bE)
          }
        case (Some(aV), Some(bV)) if aV.isObject && bV.isObject =>
          assertJsonSubset(aV.asObject.get, bV.asObject.get)
        case (aV, bV) =>
          aV must equal(bV)
      }
    }
  }
}
