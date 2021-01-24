package net.axay.simplekotlinmail.test

import net.axay.simplekotlinmail.delivery.MailerManager
import net.axay.simplekotlinmail.delivery.sendSync
import net.axay.simplekotlinmail.email.emailBuilder
import net.axay.simplekotlinmail.server.smtpServer
import net.axay.simplekotlinmail.server.start
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class SMTPServerTest {

    @Test
    fun testServer() {

        assertDoesNotThrow {

            val smtpServer = smtpServer {
                mailListener {
                    println("received email: ${it.email.plainText}")
                }
            }

            smtpServer.start(keepAlive = true)

            val email = emailBuilder {
                from("foo@bar.com")
                to("info@example.org")

                withSubject("This is an important message!")
                withPlainText("Hey, how are you?")
            }

            email.sendSync()

            MailerManager.shutdownMailers()

            smtpServer.stop()

        }

    }

    @Test
    fun startServerKeepAliveTrue() {
        val smtpServer = smtpServer()
        assert(smtpServer.start(keepAlive = true).isRunning)
        smtpServer.stop()
    }

    @Test
    fun startServerKeepAliveFalse() {
        val smtpServer = smtpServer()
        assert(smtpServer.start(keepAlive = false).isRunning)
        smtpServer.stop()
    }

}
