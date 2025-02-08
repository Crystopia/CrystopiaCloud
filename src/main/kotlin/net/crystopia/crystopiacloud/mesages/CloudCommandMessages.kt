package net.crystopia.crystopiacloud.mesages

import net.kyori.adventure.text.minimessage.MiniMessage

class CloudCommandMessages {
    private val mm = MiniMessage.miniMessage()

    val defaultCloudCommand =
        mm.deserialize("<gray>Use /cloud <option></gray>")
    val addServerFailure = mm.deserialize("<color:#ff858b>Failed to add the Server please try again</color>")
    fun addServerSuccess(name: String) =
        mm.deserialize("<color:#94ffa4>Added server successfully to the list! ($name)</color>")

    val serverRegisterSuccess = mm.deserialize("<color:#96ffd0>Server was successfully registered</color>")
    val serverRegisterFailure =
        mm.deserialize("<color:#ffb6ab>An error has occurred! The server has not been registered.</color>")
    val serverUnregisterSuccess = mm.deserialize("<color:#96ffd0>Server was successfully unregistered</color>")
    val serverUnregisterFailure =
        mm.deserialize("<color:#ffb6ab>An error has occurred! The server has not been unregistered.</color>")
    val serverCreateRegisterError =
        mm.deserialize("<color:#ffb6ab>This server could not be registered in the cloud. Change the name!</color>")
    val serverCreateAPIError =
        mm.deserialize("<color:#ffa1b5>There was an error during the creation on the server! Please try again.</color>")

    fun serverCreateSuccess(name: String) =
        mm.deserialize("<color:#b0ffc4>The server has been created and started! Use <gray><click:suggest_command:'/server ${name}'><hover:show_text:'<gray>Click to suggest Command</gray>'>/server ${name}</hover></click></gray> to join the server!</color>")

    val serverStartFail =
        mm.deserialize("<color:#ffa8b5>When starting the server, something went wrong with the connection.</color>")
    val serverStartSuccess =
        mm.deserialize("<color:#b0ffc4>The server is now starting! Please wait until it is ready.</color>")

    val serverStopFail =
        mm.deserialize("<color:#ffa8b5>When stopping the server, something went wrong with the connection.</color>")
    val serverStopSuccess =
        mm.deserialize("<color:#b0ffc4>The server is now stopping! Please wait until it is done.</color>")
    val serverDeleteFail = mm.deserialize("<color:#ffa8b5>The server could not be deleted try again.</color>")
    val serverDeleteSuccess =
        mm.deserialize("<color:#bfffb3>The server was stopped and deleted from the server.</color>")

    val serverUpdateFail =
        mm.deserialize("<color:#ffa3a5>The server could not be updated because an error occurred!</color>")
    val serverUpdateSuccess = mm.deserialize("<color:#b0ffb0>The server has been updated and restarted.</color>")
    val serverAddSuccess = mm.deserialize("<color:#bfffd0>The server has been added to the cloud! </color>")
    val serverAddFail = mm.deserialize("<color:#ff9587>The server could not be added</color>")
val serverRemoveSuccess = mm.deserialize("<color:#bfffd0>The server was successfully removed</color>")
    val serverRemoveFail = mm.deserialize("<color:#ff9587>The server was not found. Or an error has occurred!</color>")

}