package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Recommendations(
  total: Long
)
