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
                implementation("com.github.davidmoten:subethasmtp:5.2.8")

            }

        }
    }
}
