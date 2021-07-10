package net.axay.simplekotlinmail.server

import net.axay.simplekotlinmail.server.exchange.IncomingFrom
import net.axay.simplekotlinmail.server.exchange.IncomingMail
import net.axay.simplekotlinmail.server.exchange.IncomingRecipient
import org.simplejavamail.converter.EmailConverter
import org.subethamail.smtp.MessageContext
import org.subethamail.smtp.MessageHandler
import org.subethamail.smtp.MessageHandlerFactory
import java.io.InputStream

class MailHandlerFactory(
    private val fromListener: FromListener? = null,
    private val recipientListener: RecipientListener? = null,
    private val mailListener: MailListener? = null
) : MessageHandlerFactory {
    override fun create(context: MessageContext) = MailHandler(context,
        fromListener,
        recipientListener,
        mailListener
    )
}

class MailHandler(
    private val context: MessageContext,

    private val fromListener: FromListener?,
    private val recipientListener: RecipientListener?,
    private val mailListener: MailListener?
) : MessageHandler {
    private lateinit var from: String
    private val recipients = mutableListOf<String>()

    override fun from(from: String) {
        this.from = from

        if (fromListener != null) {
            val incomingFrom = IncomingFrom(from, context)
            fromListener.invoke(incomingFrom)
        }
    }

    override fun recipient(recipient: String) {
        recipients += recipient

        if (recipientListener != null) {
            val incomingRecipient = IncomingRecipient(from, recipient, recipients, context)
            recipientListener.invoke(incomingRecipient)
        }
    }

    override fun data(data: InputStream): String? {
        return if (mailListener != null) {
            val incomingMail = IncomingMail(from, recipients, EmailConverter.emlToEmail(data), context)
            mailListener.invoke(incomingMail)

            incomingMail.response
        } else null
    }

    override fun done() = Unit
}

/**
 * This listener will be invoked after the MAIL FROM command
 * during a SMTP exchange.
 *
 * Throw an exception to reject the mail,
 * see [MessageHandler.from] for a list of supported
 * exceptions.
 */
typealias FromListener = (from: IncomingFrom) -> Unit

/**
 * This listener will be invoked when a mail
 * was received.
 *
 * Throw an exception to reject the mail,
 * see [MessageHandler.data] for a list of supported
 * exceptions.
 */
typealias RecipientListener = (mail: IncomingRecipient) -> Unit

/**
 * This listener will be invoked when a mail
 * was received.
 *
 * Throw an exception to reject the mail,
 * see [MessageHandler.data] for a list of supported
 * exceptions.
 */
typealias MailListener = (mail: IncomingMail) -> Unit
