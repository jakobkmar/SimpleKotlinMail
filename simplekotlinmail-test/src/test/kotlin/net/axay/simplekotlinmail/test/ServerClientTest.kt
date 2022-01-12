package net.axay.simplekotlinmail.test

import kotlinx.coroutines.runBlocking
import net.axay.simplekotlinmail.delivery.MailerManager
import net.axay.simplekotlinmail.delivery.mailerBuilder
import net.axay.simplekotlinmail.delivery.send
import net.axay.simplekotlinmail.delivery.sendSync
import net.axay.simplekotlinmail.email.emailBuilder
import net.axay.simplekotlinmail.server.smtpServer
import net.axay.simplekotlinmail.server.start
import net.axay.simplekotlinmail.server.tls.TLSContext
import net.axay.simplekotlinmail.server.tls.setupTLS
import net.axay.simplekotlinmail.server.utils.session
import net.axay.simplekotlinmail.server.utils.sslSocket
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.simplejavamail.api.mailer.config.TransportStrategy
import java.io.File

class ServerClientTest {
    private val plainText = "Hey, how are you?"

    private val email = emailBuilder {
        from("foo@bar.com")
        to("info@example.org")

        withSubject("This is an important message!")
        withPlainText(plainText)
    }

    @Test
    fun testServer() = runBlocking {
        val smtpServer = smtpServer(2500) {
            mailListener {
                println("received email: ${it.email.plainText}")
                assertEquals(it.email.plainText?.trim(), plainText)
            }
        }

        smtpServer.start(keepAlive = true)

        MailerManager.defaultMailer = mailerBuilder(port = 2500)

        email.sendSync()
        email.send().join()

        MailerManager.shutdownMailers()

        smtpServer.stop()
    }

    @Test
    fun testServerTLS() = runBlocking {
        val smtpServer = smtpServer(2500) {
            setupTLS(
                TLSContext(
                    File("./src/test/resources/keystore"), "passphrase",
                    File("./src/test/resources/truststore"), "passphrase"
                ),
                true,
                requireClientAuth = false
            )

            mailListener {
                println("received email: ${it.email.plainText}")
                assertEquals(it.email.plainText?.trim(), plainText)
                assert(it.context.session?.sslSocket != null)
            }
        }

        smtpServer.start(keepAlive = true)

        MailerManager.defaultMailer = mailerBuilder(port = 2500) {
            verifyingServerIdentity(false)
            trustingAllHosts(true)
            withTransportStrategy(TransportStrategy.SMTP_TLS)
        }

        email.sendSync()
        email.send().join()

        MailerManager.shutdownMailers()

        smtpServer.stop()
    }
}
