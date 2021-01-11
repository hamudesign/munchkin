package munchkin.steam.model

import io.circe.generic.JsonCodec

@JsonCodec
case class AppDetailsData(
  `type`: String,
  name: String,
  steam_appid: Long,
  required_age: Int,
  is_free: Boolean,
  detailed_description: String,
  about_the_game: String,
  short_description: String,
  supported_languages: String,
  header_image: String,
  developers: Option[List[String]],
  publishers: List[String],
  price_overview: Option[PriceOverview],
  platforms: Platforms,
  metacritic: Option[Metacritic],
  categories: Option[List[Category]],
  genres: Option[List[Genre]],
  screenshots: Option[List[Screenshot]],
  movies: Option[List[Movie]],
  recommendations: Option[Recommendations],
  release_date: ReleaseDate
)
