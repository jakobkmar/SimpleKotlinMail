package net.axay.simplekotlinmail.server.tls

/**
 * This enum lists the different TLS versions
 * which can be used together with [javax.net.ssl].
 *
 * @param protocolVersion The string representing the TLS version.
 * @param cipherSuites A list of strings representing the cipher suites which should be used
 * for that version. The names refer to the names of the enum values from [sun.security.ssl.CipherSuite].
 *
 * See: https://www.iana.org/assignments/tls-parameters/tls-parameters.xhtml
 */
enum class TLSVersions(
    val protocolVersion: String,
    val cipherSuites: List<String>
) {
    TLS_1_3(
        "TLSv1.3",
        listOf(
            "TLS_AES_256_GCM_SHA384",
            "TLS_AES_128_GCM_SHA256",
            "TLS_CHACHA20_POLY1305_SHA256"
        )
    ),
    TLS_1_2(
        "TLSv1.2",
        listOf(
            "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
            "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
            "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256"
        )
    ),
    @Deprecated("Support is running out. Since TLS 1.1 uses the non-collision-resistant hash function SHA-1 to create the signature, the BSI advises against its use.")
    TLS_1_1(
        "TLSv1.1",
        listOf(
            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"
        )
    ),
    @Deprecated("Support is running out. No longer complies with the Payment Card Industry Data Security Standard (PCI DSS) in payment transactions as of June 30, 2018.")
    TLS_1_0(
        "TLSv1",
        listOf(
            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"
        )
    )
}
