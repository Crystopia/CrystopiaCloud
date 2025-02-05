package net.crystopia.crystopiacloud.webapi

import com.velocitypowered.api.proxy.server.ServerInfo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.crystopia.crystopiacloud.config.ConfigManager
import net.crystopia.crystopiacloud.config.data.ServerData
import net.crystopia.crystopiacloud.functions.ServerManager
import java.util.UUID

class WebAPI {

    fun main() {
        embeddedServer(Netty, port = ConfigManager.settings.api.APIPort!!.toInt()) {
            install(ContentNegotiation) {
                json(Json { prettyPrint = true })
            }

            routing {
                get("/allservers") {
                    val serverList = ConfigManager.settings.servers.map { server ->
                        ServerInfo(
                            port = server.value.port,
                            ip = server.value.ip,
                            name = server.value.name,
                            uuid = server.value.uuid,
                            enabled = server.value.enabled
                        )
                    }
                    call.respond(HttpStatusCode.OK, serverList)
                }
                post("/addserver") {

                    val header = call.request.headers["Authorization"]
                    if (header == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                    if (header.equals(ConfigManager.settings.api.apiToken)) {

                        val name = call.request.queryParameters["name"]
                        val enabled = call.request.queryParameters["enabled"]
                        val ip = call.request.queryParameters["ip"]
                        val port = call.request.queryParameters["port"]
                        val motd = call.request.queryParameters["motd"]!!

                        ConfigManager.settings.servers.put(
                            name.toString(), ServerData(
                                name = name.toString(),
                                uuid = UUID.randomUUID().toString(),
                                ip = ip.toString(),
                                motd = motd,
                                enabled = enabled.toBoolean(),
                                port = port!!.toInt()
                            )
                        )

                        ServerManager().addServer(name.toString(), ip.toString(), port.toInt())

                        call.respond(HttpStatusCode.OK)
                        call.respond(HttpStatusCode.NoContent)


                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }

                }
                post("/registerserver") {

                    val header = call.request.headers["Authorization"]
                    if (header == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                    if (header.equals(ConfigManager.settings.api.apiToken)) {

                        val name = call.request.queryParameters["name"]
                        val ip = call.request.queryParameters["ip"]
                        val port = call.request.queryParameters["port"]

                        ServerManager().addServer(name.toString(), ip.toString(), port!!.toInt())

                        call.respond(HttpStatusCode.OK)
                        call.respond(HttpStatusCode.NoContent)


                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }

                }
                post("/removeserver") {

                    val header = call.request.headers["Authorization"]
                    if (header == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                    if (header.equals(ConfigManager.settings.api.apiToken)) {

                        val name = call.request.queryParameters["name"]

                        ConfigManager.settings.servers.remove(
                            name.toString(),
                        )

                        ServerManager().removeServer(name.toString())

                        call.respond(HttpStatusCode.OK)
                        call.respond(HttpStatusCode.NoContent)


                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }

                }
                post("/unregisterserver") {

                    val header = call.request.headers["Authorization"]
                    if (header == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                    if (header.equals(ConfigManager.settings.api.apiToken)) {

                        val name = call.request.queryParameters["name"]

                        ServerManager().removeServer(name.toString())

                        call.respond(HttpStatusCode.OK)
                        call.respond(HttpStatusCode.NoContent)


                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                }
                post("/enableserver") {

                    val header = call.request.headers["Authorization"]
                    if (header == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                    if (header.equals(ConfigManager.settings.api.apiToken)) {

                        val name = call.request.queryParameters["name"]

                        ConfigManager.settings.servers[name.toString()]!!.enabled = true

                        call.respond(HttpStatusCode.OK)
                        call.respond(HttpStatusCode.NoContent)


                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }

                }
                post("/disableserver") {

                    val header = call.request.headers["Authorization"]
                    if (header == null) {
                        call.respond(HttpStatusCode.Unauthorized)
                    }
                    if (header.equals(ConfigManager.settings.api.apiToken)) {

                        val name = call.request.queryParameters["name"]

                        ConfigManager.settings.servers[name.toString()]!!.enabled = false

                        call.respond(HttpStatusCode.OK)
                        call.respond(HttpStatusCode.NoContent)


                    } else {
                        call.respond(HttpStatusCode.Unauthorized)
                    }

                }
            }
        }.start(wait = false)
    }
}

@Serializable
data class ServerInfo(
    val port: Int, val ip: String, val name: String, val uuid: String, val enabled: Boolean
)