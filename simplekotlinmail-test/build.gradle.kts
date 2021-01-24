/*
 * PROJECT
 */

group = "net.axay"
version = "unspecified"

/*
 * PLUGINS
 */

plugins {
    `java-version-script`

    kotlin("multiplatform")
}

/**
 * DEPENDENCY MANAGEMENT
 */

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val jvmTest by getting {

            dependencies {

                implementation("org.junit.jupiter:junit-jupiter:5.7.0")

                implementation(project(":${rootProject.name}-core"))
                implementation(project(":${rootProject.name}-client"))
                implementation(project(":${rootProject.name}-server"))
                implementation(project(":${rootProject.name}-html"))

                implementation("org.slf4j:slf4j-simple:1.7.30")

            }

        }
    }
}

// JUNIT

tasks.withType<Test> {
    useJUnitPlatform()
}
