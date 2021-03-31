object Versions {
    const val simpleJavaMail = "6.4.4"
}

val repoName = "SimpleKotlinMail"

group = "net.axay"
version = "1.3.2"

description = "A simple, modern and coroutine based Kotlin Email API, supporting both clientside and serverside projects"

repositories {
    mavenCentral()
    jcenter()
}

plugins {
    kotlin("jvm")
    `java-library`
}

java {
    withSourcesJar()
    withJavadocJar()
}
