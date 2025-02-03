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

class JoinEvent {


    @Subscribe(priority = 1)
    fun onPlayerLogin(event: ServerPostConnectEvent) {
        try {
            val mainPackUri = URI.create(ConfigManager.settings.mainPack)
            val mainPackInfo =
                ResourcePackInfo.resourcePackInfo().uri(mainPackUri).hash("0147ab35e1646d6551f6497675e3002cb355283f")
                    .build()

            val player = event.player
            val request = ResourcePackRequest.resourcePackRequest().packs(mainPackInfo)
                .prompt(ResourcepackMessages().mainResourcePackRequest).required(true).build()

            player.sendResourcePacks(request)
        } catch (e: Exception) {
            println("ERROR: ${e.message}")
        }
    }

}