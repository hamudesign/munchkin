package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class Game(
  appid: Long,
  name: Option[String],
  playtime_forever: Long,
  img_icon_url: Option[String],
  img_logo_url: Option[String]
)
