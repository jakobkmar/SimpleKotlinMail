package net.axay.simplekotlinmail.server.exchange

import org.subethamail.smtp.MessageContext

class IncomingFrom internal constructor(
    val envelopeFrom: String,
    context: MessageContext
) : IncomingMailExchange(context)
