package net.axay.simplekotlinmail.server.tls

import net.axay.simplekotlinmail.server.SMTPServerBuilder
import java.net.InetSocketAddress
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket

/**
 * Setup TLS for this smtp server.
 *
 * @param tlsContext the SSLContext for creating a SSLSocket - use [TLSContext] to easily create a new context
 * @param requireTLS if true, TLS required for every mail exchange
 * @param protocolVersions the TLS protocol versions you wish to enable
 * @param overrideCipherSuites if true, the default enabled cipher suites will be replaced with
 * the cipher suites chosen by SimpleKotlinMail - you can see them here: [TLSVersions]
 * @param requireClientAuth [SSLSocket.setNeedClientAuth]
 * @param starttlsSocketBuilder use this builder to configure the STARTTLS socket
 */
fun SMTPServerBuilder.setupTLS(
    tlsContext: SSLContext,
    requireTLS: Boolean = false,
    protocolVersions: Array<TLSVersions> = arrayOf(TLSVersions.TLS_1_3, TLSVersions.TLS_1_2),
    overrideCipherSuites: Boolean = false,
    requireClientAuth: Boolean = true,
    starttlsSocketBuilder: (SSLSocket.() -> Unit)? = null,
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

            if (overrideCipherSuites) {
                enabledCipherSuites = (
                        protocolVersions
                            .flatMapTo(LinkedHashSet()) { it.cipherSuites } intersect supportedCipherSuites.toList()
                        ).toTypedArray()
            }

            needClientAuth = requireClientAuth

            starttlsSocketBuilder?.invoke(this)
        }
    }
}
