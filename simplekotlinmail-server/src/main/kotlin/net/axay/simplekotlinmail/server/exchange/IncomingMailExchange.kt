package net.axay.simplekotlinmail.server.exchange

import org.subethamail.smtp.DropConnectionException
import org.subethamail.smtp.MessageContext
import org.subethamail.smtp.RejectException

abstract class IncomingMailExchange(val context: MessageContext) {
    /**
     * Reject this message.
     */
    fun reject(message: String? = null, statusCode: Int? = null): Nothing =
        throw RejectException(
            statusCode ?: RejectException.DEFAULT_CODE,
            message ?: RejectException.DEFAULT_MESSAGE
        )

    /**
     * Drop the connection.
     */
    fun dropConnection(message: String? = null, statusCode: Int? = null): Nothing =
        throw DropConnectionException(
            statusCode ?: DropConnectionException.DEFAULT_CODE,
            message ?: DropConnectionException.DEFAULT_MESSAGE
        )
}
