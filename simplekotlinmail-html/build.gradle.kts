plugins {
    `common-build-script`
    `java-version-script`
    `maven-publish-script`
}

dependencies {
    implementation(project(":${rootProject.name}-core"))

    api("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
}
