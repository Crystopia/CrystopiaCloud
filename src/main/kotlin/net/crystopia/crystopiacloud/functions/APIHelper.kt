package net.crystopia.crystopiacloud.functions

import net.crystopia.crystopiacloud.config.ConfigManager
import okhttp3.Headers
import okhttp3.OkHttpClient

fun getProductionResourcepack(url: String): Boolean {

    val client = OkHttpClient()

    val header: Headers = Headers.Builder().set("Authorization", ConfigManager.settings.api!!.apiToken).build()

    val request = okhttp3.Request.Builder().url(url).get().headers(header).build()


    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) return false

        return true
    }

}

fun applyToOtherServer(url: String): Boolean {

    val client = OkHttpClient()

    val header: Headers = Headers.Builder().set("Authorization", ConfigManager.settings.api!!.apiToken).build()

    val request = okhttp3.Request.Builder().url(url).get().headers(header).build()


    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) return false

        return true
    }

}


fun zipResourcepack(url: String): Boolean {

    val client = OkHttpClient()

    val header: Headers = Headers.Builder().set("Authorization", ConfigManager.settings.api!!.apiToken).build()

    val request = okhttp3.Request.Builder().url(url).get().headers(header).build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) return false

        return true
    }

}
