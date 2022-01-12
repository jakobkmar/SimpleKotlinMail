# SMTP Server

This SMTP server can be used to **receive** emails.

## Setup

To set up a new SMTP server you can use the `smtpServer` function.

```kotlin
val smtpServer = smtpServer(port = 25) {
    // access the SMTP server builder in here (more information below)
}
```

Now you can start the SMTPServer
```kotlin
// this function does not block,
// but it keeps the current thread alive until you call stop()
smtpServer.start()
```

It is good practice to stop the SMTPServer
```kotlin
smtpServer.stop()
```

___

*The following code samples all are inside an SMTP server builder.*

## Optional Configuration

#### Configuration variables

Inside the SMTP server builder, you have access to the following configuration variables:

<table>
    <thead>
        <tr>
            <th>Option</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><b>maxRecipients</b></td>
            <td>
                The maximum amount of recipients the server accepts per message. <br> <i>default</i> = <code>1000</code>
            </td>
        </tr>
        <tr>
            <td><b>maxConnections</b></td>
            <td>
                The maximum amount of connections the server allows at once. <br> <i>default</i> = <code>1000</code>
            </td>
        </tr>
        <tr>
            <td><b>prefferedMaxMessageSize</b></td>
            <td>
                The maximum size of a message. This won't be enforced, this is just an information for the connected client. <br> <i>default</i> = <code>null</code> (no limit)
            </td>
        </tr>
        <tr>
            <td><b>connectionTimeout</b></td>
            <td>
                The timeout for waiting for data on a connection. <br> <i>default</i> = <code>1 to TimeUnit.MINUTES</code> (1 minute) <br><br>
                The best way to set this is the following
                ```kotlin
                connectionTimeout = 2 to TimeUnit.MINUTES
                ```
            </td>
        </tr>
    </tbody>
</table>

#### TLS (Secure connections)

Go to the dedicated [TLS page](tls.md) for more details.

## Listeners

With listeners, you can receive and **process emails**.

### Commands

#### Mail (easiest)

Listen to the `DATA` command (called last, therefore has the most information):
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

### Reject Connections

You can reject connections after receiving any command. Both functions have parameters for a custom reponse and status code.
```kotlin
// reject the connection
it.reject()
// drop the connection
it.dropConnection()
```

### MessageContext

The context gives you more information about the current connection.
```kotlin
// get the MessageContext
it.context
// get the session
it.context.session
// example usage of session
it.context.session?.socket is SSLSocket
```
