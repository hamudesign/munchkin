package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Webm(
  `480`: String,
  max: String
)
