package net.crystopia.crystopiacloud.mesages

import net.kyori.adventure.resource.ResourcePackRequest
import net.kyori.adventure.text.minimessage.MiniMessage

class ResourcepackMessages {
    val mm = MiniMessage.miniMessage()

    val mainResourcePackRequest =
        mm.deserialize("<color:#c1ffba>Accept the custom resource pack to play on the server.</color>")
    var kickMessage = mm.deserialize("<color:#ffb2a6>You have not downloaded the resourcepack. Please try again or contact us on the Discord - crystopia.link/discord</color>")

    var productionPackFail = mm.deserialize("<color:#ff9782>The API Request to get a Production-Pack failed!</color>")
    var productionPackSuccess = mm.deserialize("<color:#75ff93>The Production-Pack is now ready to apply <gray>(Rejoin the Network)</gray></color>")
}