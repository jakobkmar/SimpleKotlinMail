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
version = "1.3.2"

description = "A simple, modern and coroutine based Kotlin Email API, supporting both clientside and serverside projects"

repositories {
    mavenCentral()
    jcenter()
}

/*
 * PLUGINS
 */

plugins {

    kotlin("jvm")

    `java-library`

    // publication

    `maven-publish`

    signing

}

/*
 * BUILD
 */

@Suppress("UnstableApiUsage")
java {
    withSourcesJar()
    withJavadocJar()
}

/*
 * PUBLISHING
 */

signing {
    sign(publishing.publications)
}

publishing {
    repositories {
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            credentials {
                username = property("ossrh.username") as String
                password = property("ossrh.password") as String
            }
        }
    }

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

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                url.set(githubUrl)

                scm {
                    connection.set("scm:git:git://github.com/bluefireoly/SimpleKotlinMail.git")
                    url.set("https://github.com/bluefireoly/SimpleKotlinMail/tree/main")
                }
            }
        }
    }
}
