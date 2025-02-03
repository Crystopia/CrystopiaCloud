package net.crystopia.crystopiacloud

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.LoginEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import net.crystopia.crystopiacloud.commands.CloudCommand
import net.crystopia.crystopiacloud.commands.ResourcePackCommand
import net.crystopia.crystopiacloud.events.JoinEvent
import net.crystopia.crystopiacloud.events.ResourcePackEvent
import org.slf4j.Logger


@Plugin(id = "crystopiacloud", name = "CrystopiaCloud", version = "0.1.0", authors = ["jesforge"])

class CrystopiaCloud {

    var server: ProxyServer? = null
    var logger: Logger? = null

    companion object {
        lateinit var instance: CrystopiaCloud
    }

    init {
        instance = this
    }

    @Inject
    fun CrystopiaCloud(server: ProxyServer, logger: Logger) {
        this.server = server
        this.logger = logger

        logger.info("Loaded CrystopiaCloud")
    }

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent?) {
        server!!.commandManager.register(
            "cloud", CloudCommand(
                proxy = server!!
            )
        )
        server!!.commandManager.register(
            "resourcepack", ResourcePackCommand()
        )
        server!!.eventManager.register(this, JoinEvent());
        server!!.eventManager.register(this, ResourcePackEvent());
    }

}
