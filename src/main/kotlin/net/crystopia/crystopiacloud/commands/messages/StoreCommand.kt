package net.crystopia.crystopiacloud.commands.messages

import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.crystopia.crystopiacloud.mesages.MessageCommandsMessages

class StoreCommand {
    val command = commandTree("store") {
        withAliases("shop")
        executes(CommandExecutor { commandSource, commandArguments ->
            commandSource.sendMessage(MessageCommandsMessages().storeMessage)
        })
    }

}
