# TLS

TLS ([Transport Layer Security](https://en.wikipedia.org/wiki/Transport_Layer_Security)) allows you to secure the Simple
Mail Transfer Protocol.

**You probably want to [let your proxy handle TLS](https://docs.nginx.com/nginx/admin-guide/mail-proxy/mail-proxy/),
this guide is for TLS in tests.**

## TLSContext (SSLContext)

SimpleKotlinMail provides a utility function allowing you to easily create a new TLSContext (actually an SSLContext).

```kotlin
val tlsContext = TLSContext(
    File("path/to/keystore"), keyStorePassphrase = "passphrase",
    File("path/to/truststore"), trustStorePassphrase = "passphrase"
)
```

If you need a keystore and truststore for **testing purposes**, you can download
both [from the OpenJDK repository](https://github.com/openjdk/jdk/tree/master/test/jdk/javax/net/ssl/etc).

## Secure the SMTP server

Use the `setupTLS` function inside an SMTP server builder.

```kotlin
smtpServer {
    setupTLS(tlsContext)
}
```

_Currently, TLSv1.3 and TLSv1.2 are enabled by default, but you can change that (example below)._

or if you need more options

```kotlin
setupTLS(
    tlsContext,
    requireTLS = true,
    protocolVersions = listOf(TLSVersions.TLS_1_3),
    requireClientAuth = true
) {
    // configure the SSLSocket to your liking
}
```

## Secure the Mailer

Inside a mailer builder, you can set the transport strategy:

```kotlin
mailerBuilder {
    // STARTTLS
    withTransportStrategy(TransportStrategy.SMTP_TLS)
    // or complete TLS encryption
    withTransportStrategy(TransportStrategy.SMTPS)
}
```
