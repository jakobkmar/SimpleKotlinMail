@file:Suppress("UNUSED_VARIABLE")

/**
 * BUILD CONSTANTS
 */

val simpleJavaMailVersion = "6.4.4"

/**
 * PLUGINS
 */

plugins {

    `common-build-script`

    kotlin("plugin.serialization") version "1.4.21"

}

/**
 * DEPENDENCY MANAGEMENT
 */

kotlin {
    sourceSets {
        val jvmMain by getting {

            dependencies {

                // SIMPLE JAVA MAIL
                api("org.simplejavamail:simple-java-mail:$simpleJavaMailVersion")
                api("org.simplejavamail:smime-module:$simpleJavaMailVersion")
                api("org.simplejavamail:batch-module:$simpleJavaMailVersion")

                // COROUTINES
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

                // SERIALIZATION
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

            }

        }
    }
}
