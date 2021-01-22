package net.axay.simplekotlinmail.server.exchange

import org.subethamail.smtp.MessageContext

class IncomingRecipient internal constructor(
    val envelopeFrom: String,
    val recipient: String,
    val currentRecipients: List<String>,
    context: MessageContext
) : IncomingMailExchange(context)
