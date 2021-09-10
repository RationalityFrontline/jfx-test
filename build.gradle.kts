
plugins {
    kotlin("jvm") version "1.5.30"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
}

group = "com.test.jfx"
version = "0.1.1"

repositories {
    mavenCentral()
}

javafx {
    version = "16"
    modules = listOf("javafx.controls")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

application {
    mainClass.set("com.test.jfx.AppKt")
    mainModule.set("test.jfx")
}