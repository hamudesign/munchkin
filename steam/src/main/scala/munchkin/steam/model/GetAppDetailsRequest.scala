package munchkin.steam.model

case class GetAppDetailsRequest(
  appids: List[Long],
  filters: List[String] = Nil
)
