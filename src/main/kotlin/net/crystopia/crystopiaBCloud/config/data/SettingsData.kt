package net.crystopia.crystopiaBCloud.config.data

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    var mainserver: String = "",
    var servers: MutableMap<String, net.crystopia.crystopiaBCloud.config.data.ServerData> = mutableMapOf(),
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