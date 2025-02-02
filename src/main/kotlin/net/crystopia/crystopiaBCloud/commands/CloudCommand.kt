package net.crystopia.crystopiaBCloud.commands

import net.crystopia.crystopiaBCloud.CrystopiaBCloud
import net.crystopia.crystopiaBCloud.functions.SeverManager
import net.crystopia.crystopiaBCloud.mesages.CloudCommandMessages
import net.kyori.adventure.platform.bungeecord.BungeeAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.plugin.Command
import net.md_5.bungee.protocol.packet.Commands

class CloudCommand(private val plugin: CrystopiaBCloud) : Command("cloud", "crystopia.command.cloud", "ccloud") {
    private val audience: BungeeAudiences = BungeeAudiences.create(plugin)

    override fun execute(player: CommandSender?, args: Array<out String>?) {
        if (args == null || args.isEmpty()) {
            if (player != null) {
                audience.sender(player).sendMessage(CloudCommandMessages().defaultCloudCommand)
            }
        }

        if (args?.get(0)?.equals("add") == true) {

            val name = args?.get(1) as String
            val host = args?.get(2) as String
            val port = args?.get(3)?.toInt()
            val motd = args?.get(4) as String

            val request = SeverManager().addServer(
                name, host, port!!, motd
            )

            if (request == true) {
                audience.sender(player!!).sendMessage(CloudCommandMessages().addServerSuccess(name))
            } else {
                audience.sender(player!!).sendMessage(CloudCommandMessages().addServerFailure)
            }
        }
    }
}