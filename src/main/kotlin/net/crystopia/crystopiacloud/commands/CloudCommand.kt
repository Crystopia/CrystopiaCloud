package net.crystopia.crystopiacloud.commands

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.ProxyServer
import net.crystopia.crystopiacloud.functions.ServerManager
import net.crystopia.crystopiacloud.mesages.CloudCommandMessages
import net.kyori.adventure.text.Component

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
                    source.source().sendMessage(Component.text("§cBenutzung: /cloud add <Name> <Host> <Port>"))
                    return
                }

                val name = args[1]
                val host = args[2]
                val port = args[3].toIntOrNull()

                if (port == null) {
                    source.source().sendMessage(Component.text("§cUngültiger Port!"))
                    return
                }

                val request = ServerManager().addServer(name, host, port)

                if (request) {
                    source.source().sendMessage(Component.text("§aServer $name erfolgreich hinzugefügt!"))
                } else {
                    source.source().sendMessage(Component.text("§cFehler beim Hinzufügen des Servers."))
                }
            }

            else -> {
                source.source().sendMessage(Component.text("§cUnbekannter Befehl. Nutze /cloud für Hilfe."))
            }
        }
    }

    override fun suggest(source: SimpleCommand.Invocation): List<String> {
        return listOf("add", "remove", "list")
    }
}
