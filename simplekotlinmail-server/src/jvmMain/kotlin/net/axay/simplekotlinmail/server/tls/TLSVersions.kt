package net.axay.simplekotlinmail.server.tls

enum class TLSVersions(
    val protocolVersion: String,
    val cipherSuites: Array<String>
) {

    TLS_1_3(
        "TLSv1.3",
        arrayOf(
            "TLS_AES_128_GCM_SHA256",
            "TLS_AES_256_GCM_SHA384"
        )
    ),

    TLS_1_2(
        "TLSv1.2",
        arrayOf(
            "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
            "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256"
        )
    ),

    @Deprecated("Support is running out. Since TLS 1.1 uses the non-collision-resistant hash function SHA-1 to create the signature, the BSI advises against its use.")
    TLS_1_1(
        "TLSv1.1",
        arrayOf(
            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"
        )
    ),

    @Deprecated("Support is running out. No longer complies with the Payment Card Industry Data Security Standard (PCI DSS) in payment transactions as of June 30, 2018.")
    TLS_1_0(
        "TLSv1",
        arrayOf(
            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA"
        )
    )

}
