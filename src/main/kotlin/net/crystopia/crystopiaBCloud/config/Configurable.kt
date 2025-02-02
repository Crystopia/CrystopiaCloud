package net.crystopia.crystopiaBCloud.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}