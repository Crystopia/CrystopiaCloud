package net.crystopia.crystopiacloud.commands

import com.velocitypowered.api.command.SimpleCommand
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import net.crystopia.crystopiacloud.config.ConfigManager
import net.crystopia.crystopiacloud.functions.applyToOtherServer
import net.crystopia.crystopiacloud.functions.getProductionResourcepack
import net.crystopia.crystopiacloud.functions.zipResourcepack
import net.crystopia.crystopiacloud.mesages.ResourcepackMessages
import net.kyori.adventure.resource.ResourcePackInfo
import net.kyori.adventure.resource.ResourcePackRequest
import net.kyori.adventure.text.Component
import java.net.URI
import java.util.UUID

class ResourcePackCommand {

    val command = commandTree("resourcepack") {
        executes(CommandExecutor { commandSource, commandArguments ->
            commandSource.sendMessage(ResourcepackMessages().defaultResourcepackCommandMessage)
        })
        literalArgument("copyToProduction") {
            executes(CommandExecutor { commandSource, commandArguments ->

                val request = getProductionResourcepack(ConfigManager.settings.api!!.copyToProductionURL)

                if (request != false) {
                    commandSource.sendMessage(ResourcepackMessages().productionPackFail)
                } else {
                    commandSource.sendMessage(ResourcepackMessages().productionPackSuccess)
                }
            })
        }
        literalArgument("applyToOtherServer") {
            executes(CommandExecutor { commandSource, commandArguments ->
                val request = applyToOtherServer(ConfigManager.settings.api!!.applyToOtherServerURL)

                if (request != false) {
                    commandSource.sendMessage(ResourcepackMessages().applyToOtherServerFail)
                } else {
                    commandSource.sendMessage(ResourcepackMessages().applyToOtherServerSuccess)
                }
            })
        }
        literalArgument("zipMainPack") {
            executes(CommandExecutor { commandSource, commandArguments ->
                val request = zipResourcepack(ConfigManager.settings.api!!.zipMainPackURL)

                if (request != false) {
                    commandSource.sendMessage(ResourcepackMessages().zipPackFail)
                } else {
                    commandSource.sendMessage(ResourcepackMessages().zipMainPackSucces)
                }
            })
        }
        literalArgument("zipDevPack") {
            executes(CommandExecutor { commandSource, commandArguments ->
                val request = zipResourcepack(ConfigManager.settings.api!!.zipDevPackURL)

                if (request != false) {
                    commandSource.sendMessage(ResourcepackMessages().zipPackFail)
                } else {
                    commandSource.sendMessage(ResourcepackMessages().zipDevPackSucces)
                }
            })
        }
        literalArgument("applyDevPack") {
            executes(CommandExecutor { commandSource, commandArguments ->
                commandSource.removeResourcePacks(UUID.fromString(ConfigManager.settings.defaultRPId))

                val mainPackUri = URI.create(ConfigManager.settings.devPack)
                val id: UUID = UUID.fromString(ConfigManager.settings.defaultRPId)

                val mainPackInfo =
                    ResourcePackInfo.resourcePackInfo().uri(mainPackUri).hash("")
                        .id(id)
                        .build()

                val player = commandSource
                val request = ResourcePackRequest.resourcePackRequest().packs(mainPackInfo)
                    .prompt(ResourcepackMessages().mainResourcePackRequest).required(true).build()

                player.sendResourcePacks(request)
                player.sendMessage(ResourcepackMessages().applyPackMessage)
            })
        }
        literalArgument("applyMainPack") {
            executes(CommandExecutor { commandSource, commandArguments ->
                commandSource.removeResourcePacks(UUID.fromString(ConfigManager.settings.defaultRPId))

                val mainPackUri = URI.create(ConfigManager.settings.mainPack)
                val id: UUID = UUID.fromString(ConfigManager.settings.defaultRPId)

                val mainPackInfo =
                    ResourcePackInfo.resourcePackInfo().uri(mainPackUri).hash("")
                        .id(id)
                        .build()

                val player = commandSource
                val request = ResourcePackRequest.resourcePackRequest().packs(mainPackInfo)
                    .prompt(ResourcepackMessages().mainResourcePackRequest).required(true).build()

                player.sendResourcePacks(request)
                player.sendMessage(ResourcepackMessages().applyPackMessage)
            })
        }
    }
}
