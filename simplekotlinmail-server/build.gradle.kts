@file:Suppress("UNUSED_VARIABLE")

/**
 * PLUGINS
 */

plugins {
    `common-build-script`
    `java-version-script`
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
                api("com.github.davidmoten:subethasmtp:5.2.8")

            }

        }
    }
}
