package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class GetOwnedGamesResponse(
  response: GetOwnedGamesResponseData
)
