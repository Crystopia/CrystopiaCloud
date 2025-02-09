package net.crystopia.crystopiacloud.commands

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.server.RegisteredServer
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.crystopia.crystopiacloud.CrystopiaCloud

class LobbyCommand {

    val command = commandTree("lobby") {
        withAliases("l", "hub", "exit")
        executes(CommandExecutor { commandSource, commandArguments ->

            commandSource as Player
            val server: RegisteredServer =
                CrystopiaCloud.instance.server!!.getServer("Lobby").get() as RegisteredServer

            commandSource.createConnectionRequest(server).fireAndForget()

        })
    }

}