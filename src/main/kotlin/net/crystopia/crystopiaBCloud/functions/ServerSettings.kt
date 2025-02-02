package net.crystopia.crystopiaBCloud.functions

import net.crystopia.crystopiaBCloud.config.ConfigManager
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.config.ServerInfo
import java.net.InetSocketAddress

class ServerSettings {

    fun renameServer(serverName: String, newName: String): Boolean {
        val proxy: ProxyServer = ProxyServer.getInstance()

        if (proxy.getServers().containsKey(serverName)) {

            proxy.servers.remove(serverName)
            val server = ConfigManager.settings.servers[serverName]!!

            server.name = newName
            ConfigManager.save()

            val proxyServer: ServerInfo =
                proxy.constructServerInfo(serverName, InetSocketAddress(server.ip, server.port), server.motd, false)
            proxy.servers.put(
                serverName, proxyServer
            )
            return true
        } else {
            return false
        }
    }

    fun editServer(serverName: String, ip: String?, port: Int?): Boolean {
        val proxy: ProxyServer = ProxyServer.getInstance()

        if (proxy.getServers().containsKey(serverName)) {

            proxy.servers.remove(serverName)
            val server = ConfigManager.settings.servers[serverName]!!

            server.ip = ip!!
            server.port = port!!
            ConfigManager.save()

            val proxyServer: ServerInfo =
                proxy.constructServerInfo(serverName, InetSocketAddress(ip, port), server.motd, false)
            proxy.servers.put(
                serverName, proxyServer
            )
            return true
        } else {
            return false
        }
    }

    fun disableServer(serverName: String): Boolean {
        val proxy: ProxyServer = ProxyServer.getInstance()

        if (proxy.getServers().containsKey(serverName)) {

            proxy.servers.remove(serverName)
            val server = ConfigManager.settings.servers[serverName]!!

            server.enabled = false
            ConfigManager.save()
            return true
        } else {
            return false
        }
    }

    fun enableServer(serverName: String): Boolean {
        val proxy: ProxyServer = ProxyServer.getInstance()

        if (!proxy.getServers().containsKey(serverName)) {
            val server = ConfigManager.settings.servers[serverName]!!

            server.enabled = false

            val proxyServer: ServerInfo =
                proxy.constructServerInfo(serverName, InetSocketAddress(server.ip, server.port), server.motd, false)
            proxy.servers.put(
                server.name, proxyServer
            )
            return true
        } else {
            return false
        }
    }

}