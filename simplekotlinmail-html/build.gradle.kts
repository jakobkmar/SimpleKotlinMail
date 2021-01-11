@file:Suppress("UNUSED_VARIABLE")

/**
 * PLUGINS
 */

plugins {
    `common-build-script`
}

/**
 * DEPENDENCY MANAGEMENT
 */

kotlin {
    sourceSets {
        val jvmMain by getting {

            dependencies {

                implementation(project(":${rootProject.name}-core"))

                // SUBETHA SMTP
                api("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")

            }

        }
    }
}
