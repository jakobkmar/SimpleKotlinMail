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

    private var ifTooMuchData = false
    private var tooMuchDataMessage: String? = null

    fun respondText(message: String) {
        response = message
    }

    fun tooMuchData(message: String? = null) {
        ifTooMuchData = true
        tooMuchDataMessage = message
    }

    override fun throwSpecificExceptions() {
        if (ifTooMuchData)
            if (tooMuchDataMessage != null)
                throw TooMuchDataException(tooMuchDataMessage)
            else throw TooMuchDataException()
    }

}
