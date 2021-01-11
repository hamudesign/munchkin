package munchkin.steam

import cats.implicits._
import cats.data.Kleisli
import cats.effect.Sync
import io.circe.syntax._
import munchkin.steam.model._
import org.http4s.implicits._
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.{Method, Uri}
import org.http4s.circe._

trait SteamEdgeClient[F[_, _]] {
  def getOwnedGames: F[GetOwnedGamesRequest, GetOwnedGamesResponse]
}

object SteamEdgeClient {

  def build[F[_]: Sync](
    endpoint: Uri,
    apiKey: String,
    client: Client[F]
  ): SteamEdgeClient[Kleisli[F, ?, ?]] =
    new SteamEdgeClient[Kleisli[F, ?, ?]] with Http4sClientDsl[F] with CirceEntityDecoder {

      def getOwnedGames: Kleisli[F, GetOwnedGamesRequest, GetOwnedGamesResponse] =
        Kleisli { req =>
          for {
            uri <- Sync[F].delay {
              endpoint
                .withPath(s"/IPlayerService/GetOwnedGames/v1/")
                .withQueryParam("key", apiKey)
                .withQueryParam("format", "json")
                .withQueryParam("input_json", req.asJson.noSpaces)
            }
            request <- Method.GET(uri)
            response <- client.expect[GetOwnedGamesResponse](request)
          } yield response
        }
    }

  def build[F[_]: Sync](
    apiKey: String,
    client: Client[F]
  ): SteamEdgeClient[Kleisli[F, ?, ?]] = build[F](
    endpoint = uri"https://api.steampowered.com",
    apiKey = apiKey,
    client = client
  )
}
