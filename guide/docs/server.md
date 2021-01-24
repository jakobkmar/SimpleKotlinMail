# SMTP Server

This SMTP server can be used to **receive** emails.

## Setup

To set up a new SMTP server you can use the `smtpServer` function.

Create an SMTP server that does nothing and listens on port 25:
```kotlin
val smtpServer = smtpServer()
```

More advanced setup:
```kotlin
val smtpServer = smtpServer(port = 25) {
    // access the SMTP server builder in here (more information below)
}
```

Now you can start the SMTPServer
```kotlin
// keep the current thread alive (non-blocking)
smtpServer.start(keepAlive = true)
// or don't keep the current thread alive
smtpServer.start(keepAlive = false)
```

It is good practice stopping the SMTPServer
```kotlin
smtpServer.stop()
```

___

*The following code samples all are inside an SMTP server builder.*

## Optional Configuration

- `maxRecipients` The maximum amount of recipients the server accepts per message.
```kotlin
maxRecipients = 4000 // (default is 1000)
```

- `maxConnections` The maximum amount of connections the server allows at once.
```kotlin
maxConnections = 2000 // (default is 1000)
```

- `prefferedMaxMessageSize` The maximum size of a message. This **won't be enforced**, this is just an information for the connected client.
```kotlin
prefferedMaxMessageSize = 8000 // (default is null)
```

- `connectionTimeout` The timeout for waiting for data on a connection.
```kotlin
connectionTimeout = 2 to TimeUnit.MINUTES // (default is 1 minute)
```

#### TLS (Secure connections)

Go to the dedicated [TLS page](tls.md) for more details.

## Listeners

#### Data (easiest)

This listener is called last (it listens to the `DATA` command) and therefore has the most information available.
```kotlin
mailListener {
    // get envelope data
    it.envelopeFrom
    it.recipients
    
    // get the email
    it.email
    
    // optional response
    it.respondText("OK message received")
    
    // if the client sent too much data
    it.tooMuchData()
}
```

Earlier available commands are:

#### From
Listen to the `MAIL FROM` command (called first, can only be called once):
```kotlin
fromListener {
    // get the envelope from
    it.envelopeFrom
}
```

#### Recipients
Listen to the `RCPT TO` command (can be called multiple times):
```kotlin
recipientListener {
    // current envelope data
    it.envelopeFrom
    it.currentRecipients // all recipients known so far (including the one responsible for this call)
    
    // get the recipient responsible for this call
    it.recipient
}
```

##### Reject Connections

You can reject connections after receiving any command. Both functions have parameters for a custom reponse and status code.
```kotlin
// reject the connection
it.reject()
// drop the connection
it.dropConnection()
```

##### Common

You can do the following inside every callback:
```kotlin
// get the MessageContext
it.context
// get the session
it.context.session
// example usage of session
it.context.session?.socket is SSLSocket
```
