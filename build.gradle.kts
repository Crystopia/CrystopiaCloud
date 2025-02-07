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
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    implementation("net.kyori", "adventure-api", "4.17.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Command API
    implementation("dev.jorel:commandapi-velocity-shade:9.7.1-SNAPSHOT")
    implementation("dev.jorel:commandapi-bukkit-kotlin:9.7.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Ktor
    implementation("io.ktor:ktor-server-core:2.3.0")
    implementation("io.ktor:ktor-server-netty:2.3.0")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
    implementation("io.ktor:ktor-server-content-negotiation:2.x.x")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.x.x")
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
