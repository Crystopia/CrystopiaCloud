package net.crystopia.crystopiacloud.config

import net.crystopia.crystopiacloud.config.data.SettingsData
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/CrystopiaCloud/config.json")

    val settings = settingsFile.loadConfig(SettingsData())

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
    }

}