package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Platforms(
  windows: Boolean,
  mac: Boolean,
  linux: Boolean
)
