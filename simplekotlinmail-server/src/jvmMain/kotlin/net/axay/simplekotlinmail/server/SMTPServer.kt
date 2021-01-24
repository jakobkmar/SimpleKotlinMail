package net.axay.simplekotlinmail.server

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.subethamail.smtp.server.SMTPServer

/**
 * Create a new SMTPServer instance.
 * In order to start this instance, call [SMTPServer.start]
 * @see SMTPServer
 */
fun smtpServer(port: Int = 25, builder: SMTPServerBuilder.() -> Unit = {}): SMTPServer =
    SMTPServerBuilder(port).apply(builder).build()

/**
 * @param keepAlive true, if the current thread should be kept alive until
 * the SMTPServer was shut down
 * @see SMTPServer.start
 */
fun SMTPServer.start(keepAlive: Boolean): SMTPServer {
    if (keepAlive)
        this@start.start()
    else runBlocking {
        GlobalScope.launch {
            this@start.start()
        }.join()
    }
    return this
}
