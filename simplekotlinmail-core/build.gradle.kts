import Common_build_script_gradle.*

plugins {
    `common-build-script`
    `java-version-script`
    `maven-publish-script`

    kotlin("plugin.serialization") version "1.5.20"
}

/**
 * DEPENDENCY MANAGEMENT
 */

dependencies {
    // SIMPLE JAVA MAIL
    api("org.simplejavamail:simple-java-mail:${Versions.simpleJavaMail}")
    api("org.simplejavamail:smime-module:${Versions.simpleJavaMail}")

    // COROUTINES
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

    // SERIALIZATION
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
}
