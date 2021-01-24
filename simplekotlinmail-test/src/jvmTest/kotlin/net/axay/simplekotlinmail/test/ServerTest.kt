package net.axay.simplekotlinmail.test

import net.axay.simplekotlinmail.server.smtpServer
import net.axay.simplekotlinmail.server.start
import org.junit.jupiter.api.Test

class ServerTest {

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
