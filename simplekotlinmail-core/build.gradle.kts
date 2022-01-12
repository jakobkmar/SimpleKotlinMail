plugins {
    `common-build-script`
    `java-version-script`
    `maven-publish-script`

    libs.plugins.kt.serialization
}

/**
 * DEPENDENCY MANAGEMENT
 */

dependencies {
    // SIMPLE JAVA MAIL
    api(libs.simpleJavaMail)
    api(libs.simpleJavaMail.smime)

    // COROUTINES
    api(libs.kt.coroutines)

    // SERIALIZATION
    api(libs.kt.serialization)
}
