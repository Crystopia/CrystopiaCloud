package net.crystopia.crystopiaBCloud

import net.crystopia.crystopiaBCloud.config.ConfigManager
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.plugin.Plugin

class CrystopiaBCloud : Plugin() {

    companion object {
        lateinit var instance: CrystopiaBCloud
    }

    override fun onEnable() {
        val settings = ConfigManager.settings

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
