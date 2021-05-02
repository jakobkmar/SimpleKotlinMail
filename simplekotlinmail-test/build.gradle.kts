plugins {
    `common-build-script`
    `java-version-script`
}

dependencies {
    implementation(project(":${rootProject.name}-core"))
    implementation(project(":${rootProject.name}-client"))
    implementation(project(":${rootProject.name}-server"))
    implementation(project(":${rootProject.name}-html"))

    implementation("org.slf4j:slf4j-simple:1.7.30")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
}

tasks.test {
    useJUnitPlatform()
}
