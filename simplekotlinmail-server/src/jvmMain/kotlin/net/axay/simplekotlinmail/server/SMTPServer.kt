package net.axay.simplekotlinmail.server

import org.subethamail.smtp.MessageHandler
import org.subethamail.smtp.helper.SimpleMessageListener
import org.subethamail.smtp.server.SMTPServer

/**
 * Create a new SMTPServer instance.
 * In order to start this instance, call [SMTPServer.start]
 * @see SMTPServer
 */
fun smtpServer(port: Int = 25, builder: SMTPServer.Builder.() -> Unit = {}): SMTPServer =
    SMTPServer.port(port).apply(builder).build()

/**
 *
 */
fun SMTPServer.Builder.mailListener(listener: MailListener): SMTPServer.Builder =
    messageHandlerFactory(MailHandlerFactory(listener))
