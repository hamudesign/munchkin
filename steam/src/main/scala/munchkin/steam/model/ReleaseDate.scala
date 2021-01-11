package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class ReleaseDate(
  coming_soon: Boolean,
  date: String
)
