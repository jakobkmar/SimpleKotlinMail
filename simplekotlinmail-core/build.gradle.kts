plugins {
    `common-build-script`
    `java-version-script`
    `maven-publish-script`

    kotlin("plugin.serialization")
}

dependencies {
    api(libs.simpleJavaMail)
    api(libs.simpleJavaMail.smime)

    api(libs.kt.serialization)
}
