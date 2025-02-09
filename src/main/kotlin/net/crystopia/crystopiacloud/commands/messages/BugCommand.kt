package net.crystopia.crystopiacloud.commands.messages

import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.crystopia.crystopiacloud.mesages.MessageCommandsMessages

class BugCommand {

    val command = commandTree("bugs") {
        executes(CommandExecutor { commandSource, commandArguments ->
            commandSource.sendMessage(MessageCommandsMessages().bugMessage)
        })
    }

}
