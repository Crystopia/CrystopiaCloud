package net.crystopia.crystopiacloud.config

import net.crystopia.crystopiacloud.config.data.APIData
import net.crystopia.crystopiacloud.config.data.ServerData
import net.crystopia.crystopiacloud.config.data.SettingsData
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/CrystopiaCloud/config.json")

    val settings = settingsFile.loadConfig(
        SettingsData(
            mainserver = "",
            api = APIData(
                applyToOtherServerURL = "",
                productionFileName = "",
                productionPath = "",
                apiToken = "",
                productionURL = ""
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