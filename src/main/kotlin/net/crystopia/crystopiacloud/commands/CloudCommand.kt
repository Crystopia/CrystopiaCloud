package net.crystopia.crystopiacloud.commands

import dev.jorel.commandapi.CommandTree
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.kotlindsl.*
import net.crystopia.crystopiacloud.config.ConfigManager
import net.crystopia.crystopiacloud.config.data.ServerData
import net.crystopia.crystopiacloud.functions.ServerManager
import net.crystopia.crystopiacloud.mesages.CloudCommandMessages
import net.kyori.adventure.text.Component
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class CloudCommand {

    val command = commandTree("cloud") {
        withPermission("crystopia.command.cloud")
        executes(CommandExecutor { commandSource, commandArguments ->
            commandSource.sendMessage(CloudCommandMessages().defaultCloudCommand)
        })
        literalArgument("register") {
            stringArgument("name") {
                executes(CommandExecutor { commandSource, commandArguments ->

                    val name = commandArguments[0].toString()
                    val host = ConfigManager.settings.servers[name]!!.ip
                    val port = ConfigManager.settings.servers[name]!!.port

                    val request = ServerManager().registerServer(name, host, port)

                    if (request) {
                        commandSource.sendMessage(CloudCommandMessages().serverRegisterSuccess)
                    } else {
                        commandSource.sendMessage(CloudCommandMessages().serverRegisterFailure)
                    }
                })
            }
        }
        literalArgument("unregister") {
            stringArgument("name") {
                executes(CommandExecutor { commandSource, commandArguments ->

                    val name = commandArguments[0].toString()

                    val request = ServerManager().unregisterServer(name)

                    if (request) {
                        commandSource.sendMessage(CloudCommandMessages().serverUnregisterSuccess)
                    } else {
                        commandSource.sendMessage(CloudCommandMessages().serverUnregisterFailure)
                    }
                })
            }
        }
        literalArgument("create") {
            stringArgument("name") {
                stringArgument("hostAddress") {
                    integerArgument("port") {
                        textArgument("serverOptions") {
                            stringArgument("templateName") {
                                executes(CommandExecutor { commandSource, commandArguments ->

                                    val name = commandArguments[0].toString()
                                    val host = commandArguments[1].toString()
                                    val port = commandArguments[2].toString().toInt()
                                    val serverOptions = commandArguments[3].toString()
                                    val templateName = commandArguments[4].toString()

                                    val servermanager = ServerManager().addServer(name, host, port)

                                    if (servermanager == false) {
                                        commandSource.sendMessage(CloudCommandMessages().serverCreateRegisterError)
                                        return@CommandExecutor
                                    }

                                    val client = OkHttpClient()

                                    val json = """{
                                    "Name": "${name}",
                                    "Host": "$host",
                                    "Port": $port,
                                    "ServerOptions": "$serverOptions",
                                    "TemplateName": "$templateName"
                                    }""".trimMargin()

                                    val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
                                    val requestHeader =
                                        Headers.Builder().set("Authorization", ConfigManager.settings.api.apiToken)
                                            .build()


                                    val request = Request.Builder()
                                        .url(ConfigManager.settings.api.baseDaemonAPIURL + "/createServer")
                                        .post(requestBody).headers(requestHeader).build()

                                    client.newCall(request).execute().use { response ->

                                        if (!response.isSuccessful) {
                                            commandSource.sendMessage(CloudCommandMessages().serverCreateAPIError)
                                            return@CommandExecutor
                                        }

                                        commandSource.sendMessage(CloudCommandMessages().serverCreateSuccess(name))
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
        literalArgument("start") {
            stringArgument("name") {

                executes(CommandExecutor { commandSource, commandArguments ->

                    val name = commandArguments[0].toString()
                    val host = ConfigManager.settings.servers[name]!!.ip

                    val client = OkHttpClient()

                    val json = """{
                                    "Name": "$name",
                                    "Host": "$host"
                                    }""".trimMargin()

                    val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
                    val requestHeader =
                        Headers.Builder().set("Authorization", ConfigManager.settings.api.apiToken).build()


                    val request = Request.Builder().url(ConfigManager.settings.api.baseDaemonAPIURL + "/startServer")
                        .post(requestBody).headers(requestHeader).build()

                    client.newCall(request).execute().use { response ->

                        if (!response.isSuccessful) {
                            commandSource.sendMessage(CloudCommandMessages().serverStartFail)
                            return@CommandExecutor
                        }

                        commandSource.sendMessage(CloudCommandMessages().serverStartSuccess)
                    }
                })
            }
        }
        literalArgument("stop") {
            stringArgument("name") {

                executes(CommandExecutor { commandSource, commandArguments ->

                    val name = commandArguments[0].toString()
                    val host = ConfigManager.settings.servers[name]!!.ip

                    val client = OkHttpClient()

                    val json = """{
                                    "Name": "$name",
                                    "Host": "$host"
                                    }""".trimMargin()

                    val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
                    val requestHeader =
                        Headers.Builder().set("Authorization", ConfigManager.settings.api.apiToken).build()


                    val request = Request.Builder().url(ConfigManager.settings.api.baseDaemonAPIURL + "/stopServer")
                        .post(requestBody).headers(requestHeader).build()

                    client.newCall(request).execute().use { response ->

                        if (!response.isSuccessful) {
                            commandSource.sendMessage(CloudCommandMessages().serverStopFail)
                            return@CommandExecutor
                        }

                        commandSource.sendMessage(CloudCommandMessages().serverStopSuccess)
                    }
                })
            }
        }
        literalArgument("restart") {
            stringArgument("name") {

                executes(CommandExecutor { commandSource, commandArguments ->

                    val name = commandArguments[0].toString()
                    val host = ConfigManager.settings.servers[name]!!.ip

                    val client = OkHttpClient()

                    val json = """{
                                    "Name": "$name",
                                    "Host": "$host"
                                    }""".trimMargin()

                    val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
                    val requestHeader =
                        Headers.Builder().set("Authorization", ConfigManager.settings.api.apiToken).build()


                    val request = Request.Builder().url(ConfigManager.settings.api.baseDaemonAPIURL + "/startServer")
                        .post(requestBody).headers(requestHeader).build()
                    val request2 = Request.Builder().url(ConfigManager.settings.api.baseDaemonAPIURL + "/stopServer")
                        .post(requestBody).headers(requestHeader).build()

                    client.newCall(request).execute().use { response ->

                        if (!response.isSuccessful) {
                            commandSource.sendMessage(CloudCommandMessages().serverStopFail)
                            return@CommandExecutor
                        }
                        commandSource.sendMessage(CloudCommandMessages().serverStopSuccess)
                        commandSource.sendMessage(CloudCommandMessages().addServerSuccess(name))
                        client.newCall(request2).execute().use { response ->

                            if (!response.isSuccessful) {
                                commandSource.sendMessage(CloudCommandMessages().serverStartFail)
                                return@CommandExecutor
                            }
                            commandSource.sendMessage(CloudCommandMessages().serverStartSuccess)
                        }
                    }


                })
            }
        }
        literalArgument("delete") {
            stringArgument("name") {
                stringArgument("delete? - type Force", optional = true) {
                    executes(CommandExecutor { commandSource, commandArguments ->

                        val name = commandArguments[0].toString()
                        val force = commandArguments[1].toString()
                        val host = ConfigManager.settings.servers[name]!!.ip

                        val client = OkHttpClient()

                        val json = """{
                                    "Name": "$name",
                                    "Host": "$host",
                                    "Action": "$force"
                                    }""".trimMargin()

                        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
                        val requestHeader =
                            Headers.Builder().set("Authorization", ConfigManager.settings.api.apiToken).build()

                        val request =
                            Request.Builder().url(ConfigManager.settings.api.baseDaemonAPIURL + "/deleteServer")
                                .post(requestBody).headers(requestHeader).build()

                        client.newCall(request).execute().use { response ->

                            if (!response.isSuccessful) {
                                commandSource.sendMessage(CloudCommandMessages().serverDeleteFail)
                                return@CommandExecutor
                            }

                            ServerManager().unregisterServer(name)
                            ConfigManager.settings.servers.remove(name)
                            ConfigManager.save()

                            commandSource.sendMessage(CloudCommandMessages().serverDeleteSuccess)
                        }
                    })
                }
            }
        }
        literalArgument("update") {
            stringArgument("name") {
                textArgument("serverOptions") {
                    integerArgument("port", optional = true) {
                        executes(CommandExecutor { commandSource, commandArguments ->

                            val name = commandArguments[0].toString()
                            val host = ConfigManager.settings.servers[name]!!.ip
                            val serverOptions = commandArguments[1].toString()
                            var port = commandArguments[2].toString().toInt()

                            if (port == null) {
                                port = ConfigManager.settings.servers[name]!!.port
                            }

                            val client = OkHttpClient()

                            val json = """{
                                    "Name": "${name}",
                                    "Host": "$host",
                                    "Port": $port,
                                    "ServerOptions": "$serverOptions"
                                    }""".trimMargin()

                            val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)
                            val requestHeader =
                                Headers.Builder().set("Authorization", ConfigManager.settings.api.apiToken).build()


                            val request =
                                Request.Builder().url(ConfigManager.settings.api.baseDaemonAPIURL + "/updateServer")
                                    .post(requestBody).headers(requestHeader).build()

                            client.newCall(request).execute().use { response ->

                                if (!response.isSuccessful) {
                                    commandSource.sendMessage(CloudCommandMessages().serverUpdateFail)
                                    return@CommandExecutor
                                }

                                commandSource.sendMessage(CloudCommandMessages().serverUpdateSuccess)
                            }
                        })
                    }
                }
            }
        }
        literalArgument("add") {
            stringArgument("name") {
                textArgument("host") {
                    integerArgument("port") {
                        executes(CommandExecutor { commandSource, commandArguments ->

                            val name = commandArguments[0].toString()
                            val host = commandArguments[1].toString()
                            var port = commandArguments[2].toString().toInt()


                            val request = ServerManager().addServer(name, host, port)

                            if (request == false) {

                                commandSource.sendMessage(CloudCommandMessages().serverAddFail)

                            } else {
                                commandSource.sendMessage(CloudCommandMessages().serverAddSuccess)
                            }

                        })
                    }
                }
            }
        }
        literalArgument("remove") {
            stringArgument("name") {
                executes(CommandExecutor { commandSource, commandArguments ->

                    val name = commandArguments[0].toString()

                    val request = ServerManager().removeServer(name)

                    if (request == false) {

                        commandSource.sendMessage(CloudCommandMessages().serverRemoveFail)

                    } else {
                        commandSource.sendMessage(CloudCommandMessages().serverRemoveSuccess)
                    }

                })
            }
        }
    }
}