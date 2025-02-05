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
            }
        }.start(wait = false)
    }
}

@Serializable
data class ServerInfo(
    val port: Int,
    val ip: String,
    val name: String,
    val uuid: String,
    val enabled: Boolean
)