package net.crystopia.crystopiacloud.functions

import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.ServerInfo
import net.crystopia.crystopiacloud.CrystopiaCloud
import net.crystopia.crystopiacloud.config.ConfigManager
import java.net.InetSocketAddress


fun renameServer(serverName: String, newName: String): Boolean {
    val proxy: ProxyServer = CrystopiaCloud.instance.server!!

    val registeredServer = proxy.getServer(serverName)
    if (registeredServer.isEmpty) {
        println("Server $serverName has not been registered!")
        return false
    }

    val server = ConfigManager.settings.servers[serverName] ?: return false

    proxy.unregisterServer(registeredServer.get().serverInfo)

    server.name = newName
    ConfigManager.save()

    val serverInfo = ServerInfo(newName, InetSocketAddress(server.ip, server.port))
    proxy.registerServer(serverInfo)

    println("Server renamed: $serverName")
    return true
}

fun editServer(serverName: String, ip: String?, port: Int?): Boolean {
    val proxy: ProxyServer = CrystopiaCloud.instance.server!!

    val registeredServer = proxy.getServer(serverName)
    if (registeredServer.isEmpty) {
        println("Server $serverName doesn't exist.")
        return false
    }

    val server = ConfigManager.settings.servers[serverName] ?: return false

    proxy.unregisterServer(registeredServer.get().serverInfo)

    server.ip = ip ?: server.ip
    server.port = port ?: server.port
    ConfigManager.save()

    val serverInfo = ServerInfo(serverName, InetSocketAddress(server.ip, server.port))
    proxy.registerServer(serverInfo)

    println("Server $serverName has been edited.")
    return true
}

fun disableServer(serverName: String): Boolean {
    val proxy: ProxyServer = CrystopiaCloud.instance.server!!

    val registeredServer = proxy.getServer(serverName)
    if (registeredServer.isEmpty) {
        println("Server $serverName is already disabled.")
        return false
    }

    proxy.unregisterServer(registeredServer.get().serverInfo)

    val server = ConfigManager.settings.servers[serverName] ?: return false
    server.enabled = false
    ConfigManager.save()

    println("Server $serverName disabled.")
    return true
}

fun enableServer(serverName: String): Boolean {
    val proxy: ProxyServer = CrystopiaCloud.instance.server!!

    if (proxy.getServer(serverName).isPresent) {
        println("Server $serverName is already enabled.")
        return false
    }

    val server = ConfigManager.settings.servers[serverName] ?: return false
    server.enabled = true
    ConfigManager.save()

    val serverInfo = ServerInfo(serverName, InetSocketAddress(server.ip, server.port))
    proxy.registerServer(serverInfo)

    println("Server $serverName enabled.")
    return true
}
