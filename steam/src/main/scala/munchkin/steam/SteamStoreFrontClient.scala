package munchkin.steam

import cats.implicits._
import cats.data.Kleisli
import cats.effect.Sync
import munchkin.steam.model._
import org.http4s.implicits._
import org.http4s.{Method, Uri}
import org.http4s.circe.CirceEntityDecoder
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl

trait SteamStoreFrontClient[F[_, _]] {
  def getAppDetails: F[GetAppDetailsRequest, GetAppDetailsResponse]
}

object SteamStoreFrontClient {

  def build[F[_]: Sync](
    endpoint: Uri,
    client: Client[F]
  ): SteamStoreFrontClient[Kleisli[F, *, *]] =
    new SteamStoreFrontClient[Kleisli[F, *, *]] with Http4sClientDsl[F] with CirceEntityDecoder {

      def getAppDetails: Kleisli[F, GetAppDetailsRequest, GetAppDetailsResponse] =
        Kleisli { req =>
          for {
            uri <- Sync[F].delay {
              endpoint
                .withPath(s"/api/appdetails/")
                .withQueryParam("appids", req.appids.mkString(","))
                .withQueryParam("filters", req.filters.mkString(","))
            }
            request <- Method.GET(uri)
            response <- client.expect[GetAppDetailsResponse](request)
          } yield response
        }
    }

  def build[F[_]: Sync](
    client: Client[F]
  ): SteamStoreFrontClient[Kleisli[F, *, *]] = build[F](
    endpoint = uri"http://store.steampowered.com",
    client = client
  )
}
