package net.axay.simplekotlinmail.server.tls

import java.io.File
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

/**
 * Create a new SSLContext (only using TLS) from the given key and trust store.
 */
@Suppress("FunctionName")
fun TLSContext(
    keyStore: File,
    keyStorePassphrase: String,
    trustStore: File,
    trustStorePassphrase: String
): SSLContext = SSLContext.getInstance("TLS").apply {
    val keyStorePassphraseArray = keyStorePassphrase.toCharArray()

    init(
        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).apply {
            init(
                KeyStore.getInstance(KeyStore.getDefaultType()).apply {
                    load(keyStore.inputStream(), keyStorePassphraseArray)
                },
                keyStorePassphraseArray
            )
        }.keyManagers,

        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
            init(
                KeyStore.getInstance(KeyStore.getDefaultType()).apply {
                    load(trustStore.inputStream(), trustStorePassphrase.toCharArray())
                }
            )
        }.trustManagers,

        null
    )
}
