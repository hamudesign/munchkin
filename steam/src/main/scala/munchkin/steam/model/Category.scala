package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Category(
  `id`: Long,
  description: String
)
