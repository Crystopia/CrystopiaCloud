package net.crystopia.crystopiacloud.commands

import com.velocitypowered.api.command.SimpleCommand
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

class ResourcePackCommand : SimpleCommand {

    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source()
        val args = invocation.arguments()

        if (args.isEmpty()) {
            source.sendMessage(Component.text("§cPlease use a correct Option!"))
            return
        }

        when (args[0]) {
            "copyToProduction" -> {

                val request = getProductionResourcepack(ConfigManager.settings.api!!.productionURL)

                if (request != false) {
                    source.sendMessage(ResourcepackMessages().productionPackFail)
                } else {
                    source.sendMessage(ResourcepackMessages().productionPackSuccess)
                }

            }

            "applyToOtherServer" -> {
                val request = applyToOtherServer(ConfigManager.settings.api!!.applyToOtherServerURL)

                if (request != false) {

                } else {

                }
            }

            "zipMainPack" -> {
                val request = zipResourcepack(ConfigManager.settings.api!!.zipMainPack)

                if (request != false) {

                } else {

                }
            }

            "zipDevPack" -> {
                val request = zipResourcepack(ConfigManager.settings.api!!.zipDevPack)

                if (request != false) {

                } else {

                }
            }

            "applyDevPack" -> {
                source.removeResourcePacks(UUID.fromString(ConfigManager.settings.defaultRPId))

                val mainPackUri = URI.create(ConfigManager.settings.devPack)
                val id: UUID = UUID.fromString(ConfigManager.settings.defaultRPId)

                val mainPackInfo =
                    ResourcePackInfo.resourcePackInfo().uri(mainPackUri).hash("")
                        .id(id)
                        .build()

                val player = source
                val request = ResourcePackRequest.resourcePackRequest().packs(mainPackInfo)
                    .prompt(ResourcepackMessages().mainResourcePackRequest).required(true).build()

                player.sendResourcePacks(request)
            }

            "applyMainPack" -> {
                source.removeResourcePacks(UUID.fromString(ConfigManager.settings.defaultRPId))

                val mainPackUri = URI.create(ConfigManager.settings.mainPack)
                val id: UUID = UUID.fromString(ConfigManager.settings.defaultRPId)

                val mainPackInfo =
                    ResourcePackInfo.resourcePackInfo().uri(mainPackUri).hash("")
                        .id(id)
                        .build()

                val player = source
                val request = ResourcePackRequest.resourcePackRequest().packs(mainPackInfo)
                    .prompt(ResourcepackMessages().mainResourcePackRequest).required(true).build()

                player.sendResourcePacks(request)
            }

            else -> {
                source.sendMessage(Component.text("§cPlease use a correct Option!"))
            }
        }
    }

    override fun suggest(invocation: SimpleCommand.Invocation): List<String> {
        return listOf("copyToProduction", "applyToOtherServer", "applyDevPack", "applyMainPack", "zipMainPack", "zipDevPack")
    }
}
