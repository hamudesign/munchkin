package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Genre(
  `id`: String,
  description: String
)
