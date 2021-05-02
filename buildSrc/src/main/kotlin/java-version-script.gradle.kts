import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
}

tasks.withType<JavaCompile> {
    options.release.set(11)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
