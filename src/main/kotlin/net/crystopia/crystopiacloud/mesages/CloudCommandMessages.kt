package net.crystopia.crystopiacloud.mesages

import net.kyori.adventure.text.minimessage.MiniMessage

class CloudCommandMessages {
    private val mm = MiniMessage.miniMessage()

    val defaultCloudCommand =
        mm.deserialize("\n<color:#96b6ff>Commands Options</color>\n<st>-</st> add <gray>serverName: String, host: String, port: Int, motd :String?</gray>\n<st>-</st> remove <gray>serverName: String</gray>\n<st>-</st> edit <gray>serverName: String, ip: String?, port: Int?</gray>\n<st>-</st> rename <gray>serverName: String, newName: String</gray>\n<st>-</st> enable <gray>serverName: String</gray>\n<st>-</st> disable <gray>serverName: String</gray>")
    val addServerFailure = mm.deserialize("<color:#ff858b>Failed to add the Server please try again</color>")
    fun addServerSuccess(name: String) =
        mm.deserialize("<color:#94ffa4>Added server successfully to the list! ($name)</color>")
}