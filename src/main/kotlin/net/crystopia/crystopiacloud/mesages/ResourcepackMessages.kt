package net.crystopia.crystopiacloud.mesages

import net.kyori.adventure.resource.ResourcePackRequest
import net.kyori.adventure.text.minimessage.MiniMessage

class ResourcepackMessages {
    val mm = MiniMessage.miniMessage()

    val defaultResourcepackCommandMessage = mm.deserialize("<gray>Use /resourcepack <option></gray>")
    val mainResourcePackRequest =
        mm.deserialize("<color:#c1ffba>Accept the custom resource pack to play on the server.</color>")
    var kickMessage =
        mm.deserialize("<color:#ffb2a6>You have not downloaded the resourcepack. Please try again or contact us on the Discord - crystopia.link/discord</color>")
    var productionPackFail = mm.deserialize("<color:#ff9782>The API Request to get a Production-Pack failed!</color>")
    var productionPackSuccess =
        mm.deserialize("<color:#75ff93>The Production-Pack is now ready to apply <gray>(Rejoin the Network)</gray></color>")
    var zipMainPackSucces =
        mm.deserialize("<color:#75ff93>The production resource pack is ready to be applied! <gray>(execute /resourcepack applyMainPack)</gray></color>")
    var zipPackFail = mm.deserialize("<color:#ff9ca1>An error occurred when the request was sent</color>")
    var zipDevPackSucces =
        mm.deserialize("<color:#75ff93>The dev resource pack is ready to be applied! <gray>(execute /resourcepack applyDevPack)</gray></color>")
    var applyPackMessage = mm.deserialize("<color:#96ffd0>The resource pack has been reloaded and applied!</color>")
    var applyToOtherServerSuccess =
        mm.deserialize("<color:#a6ffbc>The copying of the files is completed all servers are up-to-date</color>")
    var applyToOtherServerFail = mm.deserialize("<color:#ff9ca2>An error has occurred! The synchronization did not work</color>")
}
