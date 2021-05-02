object Versions {
    const val simpleJavaMail = "6.5.2"
}

val repoName = "SimpleKotlinMail"

group = "net.axay"
version = "1.3.3"

description = "A simple, modern and coroutine based Kotlin Email API, supporting both clientside and serverside projects"

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm")
    `java-library`
}

java {
    withSourcesJar()
    withJavadocJar()
}
