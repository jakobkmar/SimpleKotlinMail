# SimpleKotlinMail

SimpleKotlinMail is a Kotlin Mail API, using Kotlin Coroutines all the way through.

## Features

- build emails
- send emails (you still need an external SMTP server - e.g. postfix)
- receive and process emails

## Quick start

Add the [required dependencies](https://github.com/bluefireoly/SimpleKotlinMail/wiki/Dependencies-(Gradle)).

### Build

Build an email:

```kotlin
val email = emailBuilder {
    from("no-reply@example.com")
    to("foo@bar.com")

    withSubject("Important question")
    withPlainText("Hey, how are you doing?")
}
```

### Send

Send that email:

```kotlin
// asynchronously
suspend fun main() = email.send()
// or synchronously
suspend fun main() = email.sendSync()
```

or send that email with asynchronous callbacks:

```kotlin
suspend fun main() = email.send(
    onException = { it.printStackTrace() },
    onSuccess = { println("I just sent an email!") }
)
```

### Server / Receive

Create a custom SMTPServer:

```kotlin
val smtpServer = smtpServer {
    mailListener {
        println(it.email.plainText)
    }
}.start(keepAlive = true)
```

Stop the server:

```kotlin
smtpServer.stop()
```

### Convert

To an email:
```kotlin
// EML String -> Email
string.toEmail()
// MimeMessage -> Email
mimeMessage.email
```

From an email:
```kotlin
// Email -> MimeMessage
email.mimeMessage
// Email -> EML String
email.eml
```

### HTML

Inside the email builder, you can easily access kotlinx.html:
```kotlin
emailBuilder {
    withHTML {
        div {
            h1 { +"Really important question!" }
            p { +"Hey, how are you doing?" }
        }
    }
}
```

## Project information

This project uses [SimpleJavaMail](https://www.simplejavamail.org/) to deal with java MimeMessages in a more elegant
way. On the server side, this projects depends on a fork of [SubEthaSMTP](https://github.com/davidmoten/subethasmtp).

If you use the documented functionality of SimpleKotlinMail, everything will make use
of [kotlinx.coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html).
