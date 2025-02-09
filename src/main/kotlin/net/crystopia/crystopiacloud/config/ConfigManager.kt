package net.crystopia.crystopiacloud.config

import net.crystopia.crystopiacloud.config.data.APIData
import net.crystopia.crystopiacloud.config.data.MainServerData
import net.crystopia.crystopiacloud.config.data.ServerData
import net.crystopia.crystopiacloud.config.data.SettingsData
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/CrystopiaCloud/config.json")

    val settings = settingsFile.loadConfig(
        SettingsData(
            mainserver = MainServerData(
                port = 0,
                name = "",
                ip = "",
            ),
            api = APIData(
                applyToOtherServerURL = "",
                APIPort = 0,
                apiToken = "",
                baseDaemonAPIURL = "",
                copyToProductionURL = "",
                zipDevPackURL = "",
                zipMainPackURL = ""
            ),
            defaultRPId = "",
            mainPack = "",
            devPack = "",
        )
    )

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
    }

}