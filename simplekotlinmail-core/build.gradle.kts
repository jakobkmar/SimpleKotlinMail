@file:Suppress("UNUSED_VARIABLE")

import Common_build_script_gradle.*

/**
 * PLUGINS
 */

plugins {

    `common-build-script`
    `java-version-script`

    kotlin("plugin.serialization") version "1.4.30"

}

/**
 * DEPENDENCY MANAGEMENT
 */

dependencies {
    // SIMPLE JAVA MAIL
    api("org.simplejavamail:simple-java-mail:${Versions.simpleJavaMail}")
    api("org.simplejavamail:smime-module:${Versions.simpleJavaMail}")

    // COROUTINES
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    // SERIALIZATION
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}
