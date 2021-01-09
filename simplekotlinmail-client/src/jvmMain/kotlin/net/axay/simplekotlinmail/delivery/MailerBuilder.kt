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
    MailerBuilder
        .withSMTPServer(smtpLoginInfo.host, smtpLoginInfo.port, smtpLoginInfo.username, smtpLoginInfo.password)
        .apply(builder)
        .buildMailer()

/**
 * Open a mailer builder.
 * This function automatically builds the email and
 * returns it.
 */
inline fun mailerBuilder(
    host: String?,
    port: Int?,
    username: String?,
    password: String?,
    builder: MailerRegularBuilderImpl.() -> Unit = {}
): Mailer = MailerBuilder
    .withSMTPServer(host, port, username, password)
    .apply(builder)
    .buildMailer()
