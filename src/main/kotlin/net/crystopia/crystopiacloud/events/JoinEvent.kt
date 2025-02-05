package net.crystopia.crystopiacloud.events

import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.LoginEvent
import com.velocitypowered.api.event.player.ServerPostConnectEvent
import net.crystopia.crystopiacloud.config.ConfigManager
import net.crystopia.crystopiacloud.mesages.ResourcepackMessages
import net.kyori.adventure.resource.ResourcePackInfo
import net.kyori.adventure.resource.ResourcePackRequest
import net.kyori.adventure.text.minimessage.MiniMessage
import java.net.URI
import java.util.UUID

class JoinEvent {


    @Subscribe(priority = 1)
    fun onPlayerLogin(event: ServerPostConnectEvent) {
        try {
            val mainPackUri = URI.create(ConfigManager.settings.mainPack)
            val id: UUID = UUID.fromString(ConfigManager.settings.defaultRPId)

            val mainPackInfo: ResourcePackInfo =
                ResourcePackInfo.resourcePackInfo().uri(mainPackUri).hash("").id(id).build()

            val player = event.player
            val request = ResourcePackRequest.resourcePackRequest().packs(mainPackInfo)
                .prompt(ResourcepackMessages().mainResourcePackRequest).required(true).build()

            val alreadyApplied = player.appliedResourcePacks?.any { it.id == mainPackInfo.id() } ?: false

            if (alreadyApplied) {

            } else {
                player.sendResourcePacks(request)
            }
        } catch (e: Exception) {
            println("ERROR: ${e.message}")
        }
    }

}