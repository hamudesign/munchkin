package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class GetOwnedGamesResponseData(
  game_count: Long,
  games: List[Game]
)
