package net.axay.simplekotlinmail.server.exchange

import org.subethamail.smtp.DropConnectionException
import org.subethamail.smtp.MessageContext
import org.subethamail.smtp.RejectException

abstract class IncomingMailExchange(val context: MessageContext) {
    private var ifReject = false
    private var rejectMessage: String? = null
    private var rejectCode: Int? = null

    private var ifDropConnection = false
    private var dropConnectionMessage: String? = null
    private var dropConnectionCode: Int? = null

    /**
     * Reject this message.
     */
    fun reject(message: String? = null, statusCode: Int? = null) {
        ifReject = true
        rejectMessage = message
        rejectCode = statusCode
    }

    /**
     * Drop the connection.
     */
    fun dropConnection(message: String? = null, statusCode: Int? = null) {
        ifDropConnection = true
        dropConnectionMessage = message
        dropConnectionCode = statusCode
    }

    internal fun throwExceptions() {
        if (ifReject)
            throw RejectException(
                rejectCode ?: RejectException.DEFAULT_CODE,
                rejectMessage ?: RejectException.DEFAULT_MESSAGE
            )
        if (ifDropConnection)
            throw DropConnectionException(
                dropConnectionCode ?: DropConnectionException.DEFAULT_CODE,
                dropConnectionMessage ?: DropConnectionException.DEFAULT_MESSAGE
            )
        throwSpecificExceptions()
    }

    protected open fun throwSpecificExceptions() { }
}
