package net.crystopia.crystopiacloud.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}