package net.axay.simplekotlinmail.server

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

private val serverScope = CoroutineScope(Dispatchers.IO)

/**
 * @param keepAlive true, if the current thread should be kept alive until
 * the SMTPServer was shut down
 * @see SMTPServer.start
 */
fun SMTPServer.start(keepAlive: Boolean): SMTPServer {
    if (keepAlive)
        this@start.start()
    else runBlocking {
        serverScope.launch {
            this@start.start()
        }.join()
    }
    return this
}
