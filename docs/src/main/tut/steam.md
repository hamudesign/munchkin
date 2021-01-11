---
layout: page
title:  "Steam"
section: "steam"
position: 2
---

# Steam

[![maven-central](https://img.shields.io/maven-central/v/design.hamu/munchkin-steam_2.12)](https://index.scala-lang.org/hamuhouse/munchkin-steam/munchkin-steam)


## Creating clients

```scala
import org.http4s.client.JavaNetClientBuilder
import org.http4s.client.middleware.FollowRedirect
import munchkin.steam.model._
import munchkin.steam._

// important to create client with redirect enabled
val client = FollowRedirect(5)(
  JavaNetClientBuilder[IO](blocker).create
)

// instantiate edge client
SteamEdgeClient
  .build[IO](apiKey, client)

// instantiate store front client
SteamStoreFrontClient
  .build[IO](client)
```
