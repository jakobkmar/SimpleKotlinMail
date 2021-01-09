# SimpleKotlinMail

SimpleKotlinMail is a Kotlin Mail API, using Kotlin Coroutines all the way through.

## Features

- build emails
- send emails (you still need an external SMTP server - e.g. postfix)
- receive and process emails

## Quick start

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
suspend fun main() = email.send()
```

or end that email with async callbacks:

```kotlin
suspend fun main() = email.send(
    onException = { it.printStackTrace() },
    onSuccess = { println("I just sent an email!") }
)
```

### Server

Create a custom SMTPServer:
(*planned*)

```kotlin
fun main() = smtpServer {
    receive {
        println(it.toString())
    }
}
```

## Project information

This project uses [SimpleJavaMail](https://www.simplejavamail.org/) to deal with java MimeMessages in a more elegant
way. On the server side, this projects depends on [SubEthaSMTP](https://github.com/davidmoten/subethasmtp).

If you use the documented functionality of SimpleKotlinMail, everything will make use
of [kotlinx.coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html).
