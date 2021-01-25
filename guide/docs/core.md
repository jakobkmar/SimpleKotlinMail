# Email API

## Create an email

You can create an email using the `emailBuilder` function. Inside an email builder you set everything you need.

```kotlin
val email = emailBuilder {
    from("foo@bar.com")
    to("info@example.org")

    withSubject("Important question")
    withPlainText("Hey, how are you today?")

    // and much more
}
```

A built email object is immutable.

### Send that email

Go to the [client page](client.md).

## Copy an email

If you don't want to start blank with your email builder, you can copy another email and change it to your liking.

```kotlin
val copiedEmail = email.copy {
    // modify the email
}
```

## Forward an email

```kotlin
val forwardEmail = email.forward(from = "forwardaddress@example.org") {
    prependText("This is a forwarded message.")
}
```

## Reply to an email

```kotlin
val replyEmail = email.reply(from = "replyaddress@example.org", toAll = false) {
    prependText("This is a reply message.")
}
```
