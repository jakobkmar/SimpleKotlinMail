package net.axay.simplekotlinmail.server

import org.subethamail.smtp.server.SMTPServer

/**
 * Set the MailListener for this SMTPServer.
 */
fun SMTPServer.Builder.mailListener(listener: MailListener): SMTPServer.Builder =
    messageHandlerFactory(MailHandlerFactory(listener))

class SMTPServerBuilder internal constructor(port: Int) {

    internal val builder: SMTPServer.Builder = SMTPServer.port(port)

}
