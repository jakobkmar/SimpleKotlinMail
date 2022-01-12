plugins {
    `common-build-script`
    `java-version-script`
}

dependencies {
    implementation(project(":${rootProject.name}-core"))
    implementation(project(":${rootProject.name}-client"))
    implementation(project(":${rootProject.name}-server"))
    implementation(project(":${rootProject.name}-html"))

    testImplementation("org.slf4j:slf4j-simple:1.7.32")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}
