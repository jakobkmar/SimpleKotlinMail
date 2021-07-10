package net.axay.simplekotlinmail.server.exchange

import org.simplejavamail.api.email.Email
import org.subethamail.smtp.MessageContext
import org.subethamail.smtp.TooMuchDataException

/**
 * This class represents an incoming mail, received by a SMTP server.
 */
class IncomingMail internal constructor(
    val envelopeFrom: String,
    val recipients: List<String>,
    val email: Email,
    context: MessageContext
) : IncomingMailExchange(context) {
    internal var response: String? = null

    /**
     * Set a custom response success message.
     */
    fun respondText(message: String) {
        response = message
    }

    /**
     * Reject the message if an input stream provides
     * more data than the listener can handle
     */
    fun tooMuchData(message: String? = null): Nothing =
        if (message != null) throw TooMuchDataException(message) else throw TooMuchDataException()
}
