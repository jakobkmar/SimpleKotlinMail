package net.axay.simplekotlinmail.server

import org.simplejavamail.converter.EmailConverter
import org.subethamail.smtp.MessageContext
import org.subethamail.smtp.MessageHandler
import org.subethamail.smtp.MessageHandlerFactory
import java.io.InputStream

class MailHandlerFactory(private val listener: MailListener) : MessageHandlerFactory {
    override fun create(context: MessageContext) = MailHandler(context, listener)
}

class MailHandler(
    private val context: MessageContext,
    private val listener: MailListener
) : MessageHandler {
    private lateinit var from: String
    private val recipients = mutableListOf<String>()

    override fun from(from: String) {
        this.from = from
    }

    override fun recipient(recipient: String) {
        recipients += recipient
    }

    override fun data(data: InputStream): String? {
        listener.receive(IncomingMail(from, recipients, EmailConverter.emlToEmail(data), context))
        return null
    }

    override fun done() = Unit
}

interface MailListener {
    /**
     * This function will be called when a mail
     * was received.
     *
     * Throw an exception in order to reject the mail,
     * see [MessageHandler.data] for a list of supported
     * exceptions.
     */
    fun receive(mail: IncomingMail)
}
