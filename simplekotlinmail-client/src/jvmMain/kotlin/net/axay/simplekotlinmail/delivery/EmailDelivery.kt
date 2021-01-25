package net.axay.simplekotlinmail.delivery

import kotlinx.coroutines.*
import org.simplejavamail.api.email.Email
import org.simplejavamail.api.mailer.Mailer

/**
 * Send this email.
 * @param mailer the mailer which should be used to deliver the message
 * @param awaitCompletion if true, the function will suspend until the message was sent
 * @param onException optional callback which should be executed if an error occurs
 * @param onSuccess optional callback which should be executed if the message was delivered successfully
 */
suspend fun Email.send(
    mailer: Mailer = MailerManager.defaultMailer,
    awaitCompletion: Boolean = false,
    onException: suspend (Exception) -> Unit = {},
    onSuccess: suspend () -> Unit = {}
) = coroutineScope {
    val job = launch(Dispatchers.IO) {
        try {
            mailer.sendMail(this@send, false)
            onSuccess()
        } catch (exc: Exception) {
            onException(exc)
        }
    }
    if (awaitCompletion)
        job.join()
}

/**
 * Send this email synchronously.
 * @param mailer the mailer which should be used to deliver the message
 */
fun Email.sendSync(mailer: Mailer = MailerManager.defaultMailer) = mailer.sendMail(this)
