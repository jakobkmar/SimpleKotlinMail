package net.axay.simplekotlinmail.delivery

import kotlinx.coroutines.*
import org.simplejavamail.api.email.Email
import org.simplejavamail.api.mailer.Mailer

/**
 * Send this email.
 * @param mailer the mailer which should be used to deliver the message
 * @param onException optional callback which should be executed if an error occurs
 * @param onSuccess optional callback which should be executed if the message was delivered successfully
 */
suspend fun Email.send(
    mailer: Mailer = MailerManager.defaultMailer,
    onException: suspend (Exception) -> Unit = {},
    onSuccess: suspend () -> Unit = {}
) = withContext(Dispatchers.IO) {
    try {
        mailer.sendMail(this@send, false)
        onSuccess()
    } catch (exc: Exception) {
        onException(exc)
    }
}

/**
 * Send this email synchronously.
 * @param mailer the mailer which should be used to deliver the message
 */
fun Email.sendSync(mailer: Mailer = MailerManager.defaultMailer) = mailer.sendMail(this)
