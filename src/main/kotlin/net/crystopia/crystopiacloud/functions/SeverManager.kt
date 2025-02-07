package net.crystopia.crystopiacloud.functions

import com.velocitypowered.api.proxy.server.ServerInfo
import net.crystopia.crystopiacloud.CrystopiaCloud
import net.crystopia.crystopiacloud.config.ConfigManager
import net.crystopia.crystopiacloud.config.data.ServerData
import java.io.ObjectInputFilter.Config
import java.net.InetSocketAddress
import java.util.UUID

class ServerManager {

    fun addServer(serverName: String, host: String, port: Int): Boolean {
        val proxy = CrystopiaCloud.instance.server!!

        if (proxy.getServer(serverName).isPresent) {
            println("Server $serverName is already in use")
            return false
        }

        ConfigManager.settings.servers[serverName] = ServerData(
                enabled = true,
                ip = host,
                port = port,
                uuid = UUID.randomUUID().toString(),
                name = serverName,
                motd = ""
            )
        ConfigManager.save()

        val serverInfo = ServerInfo(serverName, InetSocketAddress(host, port))
        proxy.registerServer(serverInfo)

        println("Server $serverName has been added!")
        return true
    }

    fun registerServer(serverName: String, host: String, port: Int): Boolean {
        val proxy = CrystopiaCloud.instance.server!!

        if (proxy.getServer(serverName).isPresent) {
            println("Server $serverName is already in use")
            return false
        }

        val serverInfo = ServerInfo(serverName, InetSocketAddress(host, port))
        proxy.registerServer(serverInfo)

        println("Server $serverName has been added!")
        return true
    }

    fun removeServer(serverName: String): Boolean {
        val proxy = CrystopiaCloud.instance.server!!

        val serverOptional = proxy.getServer(serverName)
        if (!serverOptional.isPresent) {
            println("Server $serverName is not present in use")
            return false
        }

        ConfigManager.settings.servers.remove(serverName)
        ConfigManager.save()

        proxy.unregisterServer(serverOptional.get().serverInfo)
        println("Server $serverName has been removed!")
        return true
    }

    fun unregisterServer(serverName: String): Boolean {
        val proxy = CrystopiaCloud.instance.server!!

        val serverOptional = proxy.getServer(serverName)
        if (!serverOptional.isPresent) {
            println("Server $serverName is not present in use")
            return false
        }

        proxy.unregisterServer(serverOptional.get().serverInfo)
        println("Server $serverName has been removed!")
        return true
    }
}
