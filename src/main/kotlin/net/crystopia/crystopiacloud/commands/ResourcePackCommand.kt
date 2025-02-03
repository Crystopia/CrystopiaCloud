package net.crystopia.crystopiacloud.commands

import com.velocitypowered.api.command.SimpleCommand
import net.kyori.adventure.text.Component

class ResourcePackCommand : SimpleCommand {

    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source()
        val args = invocation.arguments()

        if (args.isEmpty()) {
            source.sendMessage(Component.text("§eBenutzung: /resourcepack <Option>"))
            return
        }

        when (args[0].lowercase()) {
            "copyToProduction" -> {
                source.sendMessage(Component.text("§aResourcepack wird in die Produktion kopiert..."))
            }

            "copyToOtherServer" -> {
                source.sendMessage(Component.text("§aResourcepack wird auf den anderen Server kopiert..."))
            }

            "applyDevPack" -> {
                source.sendMessage(Component.text("§aEntwickler-Resourcepack wird angewendet..."))
            }

            else -> {
                source.sendMessage(Component.text("§cUnbekannte Option. Nutze: /resourcepack <copyToProduction | copyToOtherServer | applyDevPack>"))
            }
        }
    }

    override fun suggest(invocation: SimpleCommand.Invocation): List<String> {
        return listOf("copyToProduction", "copyToOtherServer", "applyDevPack")
    }
}
