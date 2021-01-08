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

            }

        }
    }
}
