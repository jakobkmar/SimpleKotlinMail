@file:Suppress("UNUSED_VARIABLE")

import Common_build_script_gradle.*

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

                api("org.simplejavamail:batch-module:${Versions.simpleJavaMail}")

            }

        }
    }
}
