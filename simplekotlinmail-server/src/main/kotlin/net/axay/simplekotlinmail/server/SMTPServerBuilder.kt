@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.simplekotlinmail.server

import org.subethamail.smtp.server.SMTPServer
import java.util.concurrent.TimeUnit

class SMTPServerBuilder internal constructor(port: Int) {
    private var fromListener: FromListener? = null
    private var recipientListener: RecipientListener? = null
    private var mailListener: MailListener? = null

    internal val builder: SMTPServer.Builder = SMTPServer.port(port)

    /**
     * The maximum amount of recipients the server accepts per message.
     */
    var maxRecipients = 1000

    /**
     * The maximum amount of connections the server allows at once.
     */
    var maxConnections = 1000

    /**
     * The maximum size of a message.
     * **This won't be enforced,** this is just an information for the connected client.
     */
    var prefferedMaxMessageSize: Int? = null

    /**
     * The timeout for waiting for data on a connection.
     */
    var connectionTimeout: Pair<Int, TimeUnit> = 1000 * 60 * 1 to TimeUnit.MILLISECONDS

    /**
     * Set the [FromListener] for this SMTPServer.
     */
    fun fromListener(listener: FromListener) {
        fromListener = listener
    }

    /**
     * Set the [RecipientListener] for this SMTPServer.
     */
    fun recipientListener(listener: RecipientListener) {
        recipientListener = listener
    }

    /**
     * Set the [MailListener] for this SMTPServer.
     */
    fun mailListener(listener: MailListener) {
        mailListener = listener
    }

    internal fun build(): SMTPServer {
        if (fromListener != null || recipientListener != null || mailListener != null)
            builder.messageHandlerFactory(MailHandlerFactory(fromListener, recipientListener, mailListener))

        builder.apply {
            maxRecipients(maxRecipients)
            maxConnections(maxConnections)
            maxMessageSize(prefferedMaxMessageSize ?: 0)
            connectionTimeout(connectionTimeout.first, connectionTimeout.second)
        }

        return builder.build()
    }
}
