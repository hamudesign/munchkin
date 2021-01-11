package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class GetOwnedGamesRequest(
  steamid: String,
  include_appinfo: Option[Boolean] = None,
  include_played_free_games: Option[Boolean] = None,
  appids_filter: List[Long] = Nil
)
