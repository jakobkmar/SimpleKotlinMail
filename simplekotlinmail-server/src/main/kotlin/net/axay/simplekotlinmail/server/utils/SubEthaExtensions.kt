package net.axay.simplekotlinmail.server.utils

import org.subethamail.smtp.MessageContext
import org.subethamail.smtp.server.Session
import javax.net.ssl.SSLSocket

/**
 * @return The session. This will probably never be null.
 */
val MessageContext.session get() = (this as? Session)

/**
 * @return The [SSLSocket], or null if the socket is not an SSLSocket.
 */
val Session.sslSocket get() = (socket as? SSLSocket)
