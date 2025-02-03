package net.crystopia.crystopiacloud.mesages

import net.kyori.adventure.resource.ResourcePackRequest
import net.kyori.adventure.text.minimessage.MiniMessage

class ResourcepackMessages {
    val mm = MiniMessage.miniMessage()

    val mainResourcePackRequest =
        mm.deserialize("<color:#c1ffba>Accept the custom resource pack to play on the server.</color>")
    var kickMessage = mm.deserialize("<color:#ffb2a6>You have not downloaded the resourcepack. Please try again or contact us on the Discord - crystopia.link/discord</color>")
}