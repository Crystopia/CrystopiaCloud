package net.crystopia.crystopiacloud.config.data

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    var mainserver: String = "",
    var defaultRPId: String = "8cac00d2-0c42-44f6-a9fb-d90fe86b9b04",
    var mainPack: String = "https://download.mc-packs.net/pack/0147ab35e1646d6551f6497675e3002cb355283f.zip",
    var devPack: String = "",
    var servers: MutableMap<String, net.crystopia.crystopiacloud.config.data.ServerData> = mutableMapOf(),
    var api: APIData
)

@Serializable
data class APIData(
    var apiToken: String = "",
    var APIPort: Int = 9900,
    var baseDaemonAPIURL: String = "",
    var copyToProductionURL: String = "",
    var applyToOtherServerURL: String = "",
    var zipMainPackURL: String = "",
    var zipDevPackURL: String = "",
)

@Serializable
data class ServerData(
    var name: String,
    var uuid: String,
    var ip: String,
    var port: Int,
    var enabled: Boolean,
    var motd: String,
)