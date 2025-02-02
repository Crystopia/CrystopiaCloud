plugins {
    kotlin("jvm") version "2.1.10"
    id("io.github.goooler.shadow") version "8.1.8"
    kotlin("plugin.serialization") version "2.1.10"
}

group = "net.crystopia"
version = "0.1.0"

repositories {
    mavenCentral()
    maven( "https://libraries.minecraft.net")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    compileOnly("net.md-5", "bungeecord-api", "1.21-R0.1-SNAPSHOT")
    implementation("net.kyori", "adventure-text-minimessage", "4.17.0")
    implementation("net.kyori", "adventure-api", "4.17.0")
    implementation("net.kyori" ,"adventure-platform-bungeecord" , "4.3.4")
    implementation("net.kyori", "adventure-text-serializer-gson", "4.17.0")
    compileOnly("com.mojang","brigadier","1.2.9")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "21"
    }
}
kotlin {
    jvmToolchain(21)
}
