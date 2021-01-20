package net.axay.simplekotlinmail.delivery

import net.axay.simplekotlinmail.data.SMTPLoginInfo
import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.mailer.MailerBuilder
import org.simplejavamail.mailer.internal.MailerRegularBuilderImpl

/**
 * Open a mailer builder.
 * This function automatically builds the email and
 * returns it.
 */
inline fun mailerBuilder(smtpLoginInfo: SMTPLoginInfo, builder: MailerRegularBuilderImpl.() -> Unit = {}): Mailer =
    mailerBuilder(smtpLoginInfo.host, smtpLoginInfo.port, smtpLoginInfo.username, smtpLoginInfo.password, builder)

/**
 * Open a mailer builder.
 * This function automatically builds the email and
 * returns it.
 */
inline fun mailerBuilder(
    host: String = "localhost",
    port: Int = 25,
    username: String? = null,
    password: String? = null,
    builder: MailerRegularBuilderImpl.() -> Unit = {}
): Mailer = MailerBuilder
    .withSMTPServer(host, port, username, password)
    .apply(builder)
    .buildMailer().apply {
        MailerManager.registerMailer(this)
    }
