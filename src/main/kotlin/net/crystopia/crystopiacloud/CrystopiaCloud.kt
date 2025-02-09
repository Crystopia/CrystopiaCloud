package net.crystopia.crystopiacloud

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIVelocityConfig
import net.crystopia.crystopiacloud.commands.CloudCommand
import net.crystopia.crystopiacloud.commands.LobbyCommand
import net.crystopia.crystopiacloud.commands.ResourcePackCommand
import net.crystopia.crystopiacloud.commands.messages.*
import net.crystopia.crystopiacloud.config.ConfigManager
import net.crystopia.crystopiacloud.events.JoinEvent
import net.crystopia.crystopiacloud.events.ResourcePackEvent
import net.crystopia.crystopiacloud.functions.ServerManager
import net.crystopia.crystopiacloud.webapi.WebAPI
import org.slf4j.Logger


@Plugin(id = "crystopiacloud", name = "CrystopiaCloud", version = "1.0.0", authors = ["jesforge"])

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

        CommandAPI.onLoad(CommandAPIVelocityConfig(server, this))

        logger.info("Loaded CrystopiaCloud")
    }

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent?) {
        CommandAPI.onEnable();

        CloudCommand()
        ResourcePackCommand()
        LobbyCommand()

        // Message Commands
        VoteCommand()
        StoreCommand()
        HelpCommand()
        GuidesCommand()
        BugCommand()
        DiscordCommand()

        server!!.eventManager.register(this, JoinEvent());
        server!!.eventManager.register(this, ResourcePackEvent());

        registerServersOnStart()
        WebAPI().main()
    }


    fun registerServersOnStart() {
        val servers = ConfigManager.settings.servers.filter { it.value.enabled }
        servers.forEach { server ->
            ServerManager().addServer(server.value.name.toString(), server.value.ip, server.value.port)
        }
    }
}
