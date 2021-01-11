package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class PriceOverview(
  currency: String,
  initial: Int,
  `final`: Int
)
