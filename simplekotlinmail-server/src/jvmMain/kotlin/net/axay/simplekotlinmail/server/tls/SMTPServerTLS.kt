package net.axay.simplekotlinmail.server.tls

import net.axay.simplekotlinmail.server.SMTPServerBuilder
import java.net.InetSocketAddress
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket

/**
 * Setup TLS for this smtp server.
 *
 * @param tlsContext The SSLContext for creating a SSLSocket. Use [TLSContext] to easily create a new context
 * @param requireTLS If true, TLS required for every mail exchange.
 * @param protocolVersions The TLS protocol versions you wish to enable.
 * @param requireClientAuth [SSLSocket.setNeedClientAuth]
 * @param socketBuilder (optional) Use this builder to configure the SSLSocket
 */
fun SMTPServerBuilder.setupTLS(
    tlsContext: SSLContext,
    requireTLS: Boolean = false,
    protocolVersions: List<TLSVersions> = listOf(TLSVersions.TLS_1_3, TLSVersions.TLS_1_2),
    requireClientAuth: Boolean = true,
    socketBuilder: (SSLSocket.() -> Unit)? = null
) {

    if (requireTLS) builder.requireTLS() else builder.enableTLS()

    builder.startTlsSocketFactory { socketIn ->
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

            socketBuilder?.invoke(this)
        }
    }

}
