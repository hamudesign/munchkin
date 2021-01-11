package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class AppDetails(
  success: Boolean,
  data: AppDetailsData
)
