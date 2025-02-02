package net.crystopia.crystopiaBCloud.functions

import net.crystopia.crystopiaBCloud.CrystopiaBCloud
import net.crystopia.crystopiaBCloud.config.ConfigManager
import net.crystopia.crystopiaBCloud.config.data.ServerData
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.config.ServerInfo
import java.net.InetSocketAddress
import java.util.*

class SeverManager {

    fun addServer(serverName: String, host: String, port: Int, motd :String?): Boolean {
        val proxy: ProxyServer = ProxyServer.getInstance()

        if (!proxy.getServers().containsKey(serverName)) {
            val serverInfo: ServerInfo =
                proxy.constructServerInfo(serverName, InetSocketAddress(host, port), "server motd", false)
            proxy.getServers().put(serverName, serverInfo)

            val uuid = UUID.randomUUID().toString()

            ConfigManager.settings.servers[serverName] = ServerData(
                name=uuid,
                uuid=uuid,
                ip=host,
                port=port,
                enabled=true,
                motd=motd!!
            )
            ConfigManager.save()

            CrystopiaBCloud.instance.logger.info("Your server $serverName has been registered!")
            return true
        } else {
            CrystopiaBCloud.instance.logger.warning("This server is already registered!")
            return false
        }
    }

    fun removeServer(serverName: String): Boolean {
        val proxy: ProxyServer = ProxyServer.getInstance()

        if (!proxy.getServers().containsKey(serverName)) {
            return false
        } else {
            proxy.getServers().remove(serverName)
            return true
        }
    }
}