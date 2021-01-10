package net.axay.simplekotlinmail.server

import org.subethamail.smtp.server.SMTPServer

/**
 * Set the MailListener for this SMTPServer.
 */
fun SMTPServer.Builder.mailListener(listener: MailListener): SMTPServer.Builder =
    messageHandlerFactory(MailHandlerFactory(listener))
