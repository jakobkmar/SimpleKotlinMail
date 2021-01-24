import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jvmVersion = JavaVersion.VERSION_11
val jvmVersionString = jvmVersion.versionString

plugins {
    `java-library`
}

/*
 * BUILD
 */

java {
    java.sourceCompatibility = jvmVersion
    java.targetCompatibility = jvmVersion
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = jvmVersionString
}

/*
 * EXTENSIONS
 */

val JavaVersion.versionString: String get() = if (majorVersion.toInt() <= 10) "1.$majorVersion" else majorVersion
