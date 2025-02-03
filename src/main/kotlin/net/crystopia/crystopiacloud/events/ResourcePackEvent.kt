package net.crystopia.crystopiacloud.events

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent
import net.crystopia.crystopiacloud.mesages.ResourcepackMessages

class ResourcePackEvent {


    @Subscribe
    fun on(event: PlayerResourcePackStatusEvent) {

        val status = event.status

        if (status == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) {
            val playerProfile = event.player.gameProfile
            event.player.disconnect(ResourcepackMessages().kickMessage)
        }
        if (status == PlayerResourcePackStatusEvent.Status.FAILED_RELOAD) {
            val playerProfile = event.player.gameProfile
            event.player.disconnect(ResourcepackMessages().kickMessage)
        }
        if (status == PlayerResourcePackStatusEvent.Status.DECLINED) {
            val playerProfile = event.player.gameProfile
            event.player.disconnect(ResourcepackMessages().kickMessage)
        }
        if (status == PlayerResourcePackStatusEvent.Status.INVALID_URL
            ) {
            val playerProfile = event.player.gameProfile
            event.player.disconnect(ResourcepackMessages().kickMessage)
        }
    }


}

