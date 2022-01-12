plugins {
    `common-build-script`
    `java-version-script`
    `maven-publish-script`
}

dependencies {
    api(project(":${rootProject.name}-core"))

    api(libs.kt.html)
}
