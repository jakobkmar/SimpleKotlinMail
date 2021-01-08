package net.axay.simplekotlinmail.email

import org.simplejavamail.api.email.Email
import org.simplejavamail.api.email.EmailPopulatingBuilder
import org.simplejavamail.converter.EmailConverter
import org.simplejavamail.email.EmailBuilder

/**
 * Open a new email builder.
 * This function automatically builds the email and
 * returns it.
 */
inline fun emailBuilder(builder: EmailPopulatingBuilder.() -> Unit): Email =
    EmailBuilder.startingBlank().apply(builder).buildEmail()

/**
 * Copy this email and open a new email builder.
 * This function automatically builds the new email and
 * returns it.
 */
inline fun Email.copy(builder: EmailPopulatingBuilder.() -> Unit): Email =
    EmailBuilder.copying(this).apply(builder).buildEmail()

/**
 * Copy this email.
 */
fun Email.copy(): Email = EmailBuilder.copying(this).buildEmail()
