package net.axay.simplekotlinmail.server

import org.subethamail.smtp.server.SMTPServer

class SMTPServerBuilder internal constructor(port: Int) {
    private var fromListener: FromListener? = null
    private var recipientListener: RecipientListener? = null
    private var mailListener: MailListener? = null

    internal val builder: SMTPServer.Builder = SMTPServer.port(port)

    /**
     * Set the FromListener for this SMTPServer.
     */
    fun fromListener(listener: FromListener) {
        fromListener = listener
    }

    /**
     * Set the RecipientListener for this SMTPServer.
     */
    fun recipientListener(listener: RecipientListener) {
        recipientListener = listener
    }

    /**
     * Set the MailListener for this SMTPServer.
     */
    fun mailListener(listener: MailListener) {
        mailListener = listener
    }

    internal fun build(): SMTPServer {
        if (fromListener != null || recipientListener != null || mailListener != null)
            builder.messageHandlerFactory(MailHandlerFactory(fromListener, recipientListener, mailListener))

        return builder.build()
    }
}
