package net.axay.simplekotlinmail.delivery

import kotlinx.coroutines.*
import org.simplejavamail.api.email.Email
import org.simplejavamail.api.mailer.Mailer

/**
 * The default mailer instance, that is used if
 * the no other instance is provided.
 */
val DEFAULT_MAILER = mailerBuilder("localhost", 25)

/**
 * Send this email.
 * @param mailer the mailer which should be used to deliver the message
 * @param onException optional callback which should be executed if an error occurs
 * @param onSuccess optional callback which should be executed if the message was delivered successfully
 */
suspend fun Email.send(
    mailer: Mailer = DEFAULT_MAILER,
    onException: suspend (Exception) -> Unit = {},
    onSuccess: suspend () -> Unit = {}
) = withContext(Dispatchers.IO) {
    try {
        mailer.sendMail(this@send)
        onSuccess()
    } catch (exc: Exception) {
        onException(exc)
    }
}
