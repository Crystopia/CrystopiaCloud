plugins {
    kotlin("jvm") version "2.1.10"
    id("io.github.goooler.shadow") version "8.1.8"
    kotlin("plugin.serialization") version "2.1.10"
}

group = "net.crystopia"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    implementation("net.kyori", "adventure-api", "4.17.0")
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
