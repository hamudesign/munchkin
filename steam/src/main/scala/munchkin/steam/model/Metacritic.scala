package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Metacritic(
  score: Int,
  url: String
)
