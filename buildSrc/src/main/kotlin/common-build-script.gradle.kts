import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

/*
 * BUILD CONSTANTS
 */

val githubUrl = "https://github.com/bluefireoly/SimpleKotlinMail"

val jvmVersion = JavaVersion.VERSION_11
val jvmVersionString = jvmVersion.versionString

/*
 * PROJECT
 */

group = "net.axay"
version = "0.0.1"

repositories {
    mavenCentral()
    jcenter()
}

/*
 * PLUGINS
 */

plugins {

    kotlin("multiplatform")

    `java-library`

    // publication

    `maven-publish`

    id("com.jfrog.bintray")

}

kotlin {
    jvm {
        withJava()
    }
}

/*
 * BUILD
 */


java {
    java.sourceCompatibility = jvmVersion
    java.targetCompatibility = jvmVersion

    withSourcesJar()
    withJavadocJar()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = jvmVersionString
}

/*
 * PUBLISHING
 */

bintray {

    user = project.findProperty("bintray.username") as? String ?: ""
    key = project.findProperty("bintray.api_key") as? String ?: ""

    setPublications(project.name)

    pkg.apply {

        repo = rootProject.name
        name = project.name

        version.name = project.version.toString()
        version.released = Date().toString()

        setLicenses("Apache-2.0")

        vcsUrl = githubUrl

    }

}

publishing {
    publications {
        create<MavenPublication>(project.name) {

            from(components["java"])

            this.groupId = project.group.toString()
            this.artifactId = project.name
            this.version = project.version.toString()

            pom {

                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name.set("bluefireoly")
                    }
                }

                url.set(githubUrl)

            }

        }
    }
}

/*
* EXTENSIONS
*/

val JavaVersion.versionString: String get() = if (majorVersion.toInt() <= 10) "1.$majorVersion" else majorVersion

fun TaskProvider<KotlinCompile>.configureJvmVersion() { get().kotlinOptions.jvmTarget = jvmVersionString }
