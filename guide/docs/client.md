# Client

## Mailer

A mailer instance is connected to an SMTP server capable of sending emails (e.g. [Postfix](http://www.postfix.org/), paid services or Gmail).

For a list of free SMTP servers have a look into [this list by mailtrap](https://blog.mailtrap.io/free-smtp-servers/). If you want to configure a send-only postfix server, read [this tutorial](https://blog.mailtrap.io/setup-smtp-server/).

#### Create a Mailer

```kotlin
val mailer = mailerBuilder(host = "your_hostname", port = 25)
```

#### Set the global default Mailer

The default mailer will be used for sending emails if no specific mailer is passed as a parameter.

```kotlin
MailerManager.defaultMailer = mailer
```

#### Shutdown all Mailers

The mailer instances keep your program alive. In order to exit your program safely, you have to shut down the mailer instances.

```kotlin
MailerManager.shutdownMailers()
```

## Send emails

Easily send your [previously built emails](core.md).

##### Using coroutines (Asynchronously)

This approach allows you to send emails without blocking the current thread. These methods only work within a `CoroutineScope`.

```kotlin
email.send() // using the defult Mailer instance
// or
email.send(mailer)
```

###### Suspend until completion

The send function returns a `Job`, therefore you can just join it.

```kotlin
email.send().join()
```

This can thrown an exception of the delivery failed, you can handle that like this:

```kotlin
kotlin.runCatching { email.send().join() }
    .onFailure { it.printStackTrace() }
    .onSuccess { println("I just sent an email!") }
```

##### Synchronously

If you need to send your emails synchronously for some reason, you can do that.
This function will throw an exception if the action fails, otherwise (on success) it will just pass.

```kotlin
email.sendSync() // using the default Mailer instance
// or
email.sendSync(mailer)
```
