package net.axay.simplekotlinmail.delivery

import kotlinx.coroutines.*
import kotlinx.coroutines.future.asDeferred
import org.simplejavamail.api.email.Email
import org.simplejavamail.api.mailer.Mailer

private val sendScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

/**
 * Send this email.
 * @param mailer the mailer which should be used to deliver the message
 */
suspend fun Email.send(mailer: Mailer = MailerManager.defaultMailer): Job {
    return sendScope.launch {
        mailer.sendMail(this@send, true)
            .asDeferred().await()
    }
}

/**
 * Send this email synchronously.
 * @param mailer the mailer which should be used to deliver the message
 */
fun Email.sendSync(mailer: Mailer = MailerManager.defaultMailer) =
    mailer.sendMail(this, false)
