package net.axay.simplekotlinmail.email

import org.simplejavamail.api.email.Email
import org.simplejavamail.converter.EmailConverter
import javax.mail.internet.MimeMessage

/**
 * Convert this MimeMessage to an Email instance.
 */
val MimeMessage.email: Email get() = EmailConverter.mimeMessageToEmail(this)

/**
 * Convert this Email instance to a MimeMessage
 */
val Email.mimeMessage: MimeMessage get() = EmailConverter.emailToMimeMessage(this)

/**
 * Convert this string to an Email instance.
 */
fun String.toEmail(): Email = EmailConverter.emlToEmail(this)
