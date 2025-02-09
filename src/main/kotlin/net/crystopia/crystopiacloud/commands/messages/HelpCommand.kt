package net.crystopia.crystopiacloud.commands.messages

import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.crystopia.crystopiacloud.mesages.MessageCommandsMessages

class HelpCommand {

    val command = commandTree("help") {
        executes(CommandExecutor { commandSource, commandArguments ->
            commandSource.sendMessage(MessageCommandsMessages().helpMessage)
        })
    }

}
