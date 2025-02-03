package net.crystopia.crystopiacloud.config.data

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    var mainserver: String = "",
    var mainPack: String = "https://download.mc-packs.net/pack/0147ab35e1646d6551f6497675e3002cb355283f.zip",
    var devPack: String = "",
    var servers: MutableMap<String, net.crystopia.crystopiacloud.config.data.ServerData> = mutableMapOf(),
)

@Serializable
data class ServerData(
    var name : String,
    var uuid : String,
    var ip: String,
    var port: Int,
    var enabled : Boolean,
    var motd : String,
)