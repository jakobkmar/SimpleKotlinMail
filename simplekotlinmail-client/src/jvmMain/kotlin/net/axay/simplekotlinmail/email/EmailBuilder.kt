package net.axay.simplekotlinmail.email

import org.simplejavamail.api.email.Email
import org.simplejavamail.api.email.EmailPopulatingBuilder
import org.simplejavamail.email.EmailBuilder

/**
 * Open a new email builder.
 * This function automatically builds the email and
 * returns it.
 */
inline fun emailBuilder(builder: EmailPopulatingBuilder.() -> Unit): Email =
    EmailBuilder.startingBlank().apply(builder).buildEmail()
