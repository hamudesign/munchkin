package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Screenshot(
  `id`: Long,
  path_thumbnail: String,
  path_full: String
)
