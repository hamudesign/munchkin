package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Movie(
  `id`: Long,
  name: String,
  thumbnail: String,
  webm: Webm
)
