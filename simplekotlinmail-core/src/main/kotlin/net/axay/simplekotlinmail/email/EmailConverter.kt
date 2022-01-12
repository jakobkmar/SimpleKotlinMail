package net.axay.simplekotlinmail.email

import jakarta.mail.internet.MimeMessage
import org.simplejavamail.api.email.Email
import org.simplejavamail.converter.EmailConverter

// TO EMAIL

/**
 * Convert this MimeMessage to an Email instance.
 */
val MimeMessage.email: Email get() = EmailConverter.mimeMessageToEmail(this)

/**
 * Convert this string to an Email instance.
 */
fun String.toEmail(): Email = EmailConverter.emlToEmail(this)

// FROM EMAIL

/**
 * Convert this Email instance to a MimeMessage
 */
val Email.mimeMessage: MimeMessage get() = EmailConverter.emailToMimeMessage(this)

/**
 * Convert this Email instance to an eml string
 */
val Email.eml: String get() = EmailConverter.emailToEML(this)
