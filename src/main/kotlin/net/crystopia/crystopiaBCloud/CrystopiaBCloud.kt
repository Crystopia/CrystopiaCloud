package net.crystopia.crystopiaBCloud

import net.crystopia.crystopiaBCloud.commands.CloudCommand
import net.crystopia.crystopiaBCloud.config.ConfigManager
import net.kyori.adventure.platform.bungeecord.BungeeAudiences
import net.md_5.bungee.api.plugin.Plugin

class CrystopiaBCloud : Plugin() {

    companion object {
        lateinit var instance: CrystopiaBCloud
    }

    init {
        instance = this
    }

    val adventure: BungeeAudiences? = null

    fun adventure(): BungeeAudiences {
        checkNotNull(this.adventure) { "Cannot retrieve audience provider while plugin is not enabled" }
        return this.adventure


    }

    override fun onEnable() {
        BungeeAudiences.create(this)
        val settings = ConfigManager.settings

        proxy.pluginManager.registerCommand(this, CloudCommand(plugin = instance))

        logger.info("Enabling CrystopiaBCloud plugin")

    }

    override fun onDisable() {
        if (this.adventure != null) {
            this.adventure.close()
        }


        logger.info("CrystopiaBCloud plugin closed")
    }
}
