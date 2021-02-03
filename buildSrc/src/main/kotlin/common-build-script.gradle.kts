import java.util.*

/*
 * BUILD CONSTANTS
 */

val githubUrl = "https://github.com/bluefireoly/SimpleKotlinMail"

val repoName = "SimpleKotlinMail"

object Versions {
    const val simpleJavaMail = "6.4.4"
}

/*
 * PROJECT
 */

group = "net.axay"
version = "1.3.1"

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
        mavenPublication {

            getComponents()["java"]

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
 * BUILD
 */

java {
    withSourcesJar()
    withJavadocJar()
}

/*
 * PUBLISHING
 */

bintray {

    user = project.findProperty("bintray.username") as? String ?: ""
    key = project.findProperty("bintray.api_key") as? String ?: ""

    setPublications("jvm")

    pkg.apply {

        repo = repoName
        name = project.name

        version.name = project.version.toString()
        version.released = Date().toString()

        setLicenses("Apache-2.0")

        vcsUrl = githubUrl

    }

}
