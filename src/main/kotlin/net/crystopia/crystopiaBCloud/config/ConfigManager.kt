package net.crystopia.crystopiaBCloud.config

import net.crystopia.crystopiaBCloud.config.data.SettingsData
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/CrystopiaBCloud/config.json")

    val settings = settingsFile.loadConfig(SettingsData())

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
    }

}