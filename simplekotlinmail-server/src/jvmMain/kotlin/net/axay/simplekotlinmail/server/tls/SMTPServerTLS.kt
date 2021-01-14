package net.axay.simplekotlinmail.server.tls

import org.subethamail.smtp.server.SMTPServer
import java.net.InetSocketAddress
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket

/**
 * Setup TLS for this smtp server.
 *
 * @param tlsContext The SSLContext for creating a SSLSocket. Use [TLSContext] to easily create a new context
 * @param protocolVersions The TLS protocol versions you wish to enable.
 * @param requireClientAuth [SSLSocket.setNeedClientAuth]
 * @param builder (optional) Use this builder to configure the SSLSocket
 */
fun SMTPServer.Builder.setupTLS(
    tlsContext: SSLContext,
    protocolVersions: List<TLSVersions> = listOf(TLSVersions.TLS_1_3, TLSVersions.TLS_1_2),
    requireClientAuth: Boolean = true,
    builder: SSLSocket.() -> Unit = {}
): SMTPServer.Builder =
    startTlsSocketFactory { socketIn ->
        (tlsContext.socketFactory.createSocket(
            socketIn, (socketIn.remoteSocketAddress as InetSocketAddress).hostString, socketIn.port, true
        ) as SSLSocket).apply {
            useClientMode = false

            enabledProtocols = (
                protocolVersions
                    .mapTo(LinkedHashSet()) { it.protocolVersion } intersect supportedProtocols.toList()
            ).toTypedArray()

            enabledCipherSuites = (
                protocolVersions
                    .flatMapTo(LinkedHashSet()) { it.cipherSuites } intersect supportedCipherSuites.toList()
            ).toTypedArray()

            needClientAuth = requireClientAuth

            builder(this)
        }
    }
