# Setup

## Repository

SimpleKotlinMail is available on jcenter.

```kotlin
repositories {
    jcenter()
}
```

## Dependencies

Kotlin DSL
```kotlin
dependencies {
    implementation("net.axay:MODULENAME:VERSION")
}
```

or Groovy DSL
```groovy
dependencies {
    implementation 'net.axay:MODULENAME:VERSION'
}
```

**Replace**:

- `VERSION` with the version you wish to use (you can [find the latest version on github](https://github.com/bluefireoly/SimpleKotlinMail/releases))
  
- `MODULE` with the names of the following modules:

### Modules

- **`simplekotlinmail-core`** **(required)**
- **`simplekotlinmail-client`** if you want to send emails
- **`simplekotlinmail-server`** if you want to receive emails
- **`simplekotlinmail-html`** if you want to use kotlinx.html inside your email builders


## JVM Version

To be able to use the inline functions of the API, you have to configure the JVM version (if you have not done that already).

```kotlin
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = jvmVersionString // <- e.g. 11
}
```