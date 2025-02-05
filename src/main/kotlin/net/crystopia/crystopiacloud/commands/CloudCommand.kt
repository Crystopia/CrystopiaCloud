package net.crystopia.crystopiacloud.commands

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.ProxyServer
import net.crystopia.crystopiacloud.functions.ServerManager
import net.crystopia.crystopiacloud.mesages.CloudCommandMessages
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

class CloudCommand(private val proxy: ProxyServer) : SimpleCommand {

    override fun execute(source: SimpleCommand.Invocation) {
        val args = source.arguments()

        if (args.isEmpty()) {
            source.source().sendMessage(CloudCommandMessages().defaultCloudCommand)
            return
        }

        when (args[0].lowercase()) {
            "add" -> {
                if (args.size < 4) {
                    source.source().sendMessage(CloudCommandMessages().defaultCloudCommand)
                    return
                }

                val name = args[1]
                val host = args[2]
                val port = args[3].toIntOrNull()

                if (port == null) {
                    source.source().sendMessage(Component.text("§cPort is required!", NamedTextColor.RED))
                    return
                }

                val request = ServerManager().addServer(name, host, port)

                if (request) {
                    source.source().sendMessage(CloudCommandMessages().addServerSuccess(name))
                } else {
                    source.source().sendMessage(CloudCommandMessages().addServerFailure)
                }
            }

            else -> {
                source.source().sendMessage(Component.text("§cUse /cloud for help!"))
            }
        }
    }

    override fun suggest(source: SimpleCommand.Invocation): List<String> {
        return listOf("add", "remove", "list")
    }
}
