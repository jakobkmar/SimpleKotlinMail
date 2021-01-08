package net.axay.simplekotlinmail.email

import org.simplejavamail.api.email.Email
import org.simplejavamail.converter.EmailConverter
import javax.mail.internet.MimeMessage

/**
 * Convert this MimeMessage to an Email instance.
 */
fun MimeMessage.toEmail() = EmailConverter.mimeMessageToEmail(this)
