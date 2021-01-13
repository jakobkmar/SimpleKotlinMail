package net.axay.simplekotlinmail.server.tls

import org.subethamail.smtp.server.SMTPServer
import java.net.InetSocketAddress
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket

/**
 * Setup TLS for this smtp server.
 *
 * @param tlsContext The SSLContext for creating a SSLSocket. Use [TLSContext] to easily create a new context
 * @param requireClientAuth [SSLSocket.setNeedClientAuth]
 * @param builder (optional) Use this builder to configure the SSLSocket
 */
fun SMTPServer.Builder.setupTLS(
    tlsContext: SSLContext,
    requireClientAuth: Boolean = true,
    builder: SSLSocket.() -> Unit = {}
): SMTPServer.Builder =
    startTlsSocketFactory {
        (tlsContext.socketFactory.createSocket(
            it, (it.remoteSocketAddress as InetSocketAddress).hostString, it.port, true
        ) as SSLSocket).apply {
            useClientMode = false

            enabledProtocols = arrayOf(
                "TLSv1.3",
                "TLSv1.2"
            )
            enabledCipherSuites = arrayOf(
                "TLS_AES_128_GCM_SHA256",
                "TLS_AES_256_GCM_SHA384",
                "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
                "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256"
            )

            needClientAuth = requireClientAuth

            builder(this)
        }
    }
