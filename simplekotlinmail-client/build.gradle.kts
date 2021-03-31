import Common_build_script_gradle.*

plugins {
    `common-build-script`
    `java-version-script`
    `maven-publish-script`
}

dependencies {
    implementation(project(":${rootProject.name}-core"))

    api("org.simplejavamail:batch-module:${Versions.simpleJavaMail}")
}
