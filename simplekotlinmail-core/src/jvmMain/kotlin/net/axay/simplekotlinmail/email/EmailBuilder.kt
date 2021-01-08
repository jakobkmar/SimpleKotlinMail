package net.axay.simplekotlinmail.email

import org.simplejavamail.api.email.Email
import org.simplejavamail.api.email.EmailPopulatingBuilder
import org.simplejavamail.email.EmailBuilder

/**
 * Open a new net.axay.simplekotlinmail.email builder.
 * This function automatically builds the net.axay.simplekotlinmail.email and
 * returns it.
 */
inline fun emailBuilder(builder: EmailPopulatingBuilder.() -> Unit): Email =
    EmailBuilder.startingBlank().apply(builder).buildEmail()

/**
 * Copy this net.axay.simplekotlinmail.email and open a new net.axay.simplekotlinmail.email builder.
 * This function automatically builds the new net.axay.simplekotlinmail.email and
 * returns it.
 */
inline fun Email.copy(builder: EmailPopulatingBuilder.() -> Unit): Email =
    EmailBuilder.copying(this).apply(builder).buildEmail()

/**
 * Copy this net.axay.simplekotlinmail.email.
 */
fun Email.copy(): Email = EmailBuilder.copying(this).buildEmail()

/**
 * Reply to this net.axay.simplekotlinmail.email.
 * This functions opens a new net.axay.simplekotlinmail.email builder, automatically builds
 * the new net.axay.simplekotlinmail.email and returns it.
 * @param from optional from recipient address
 * @param toAll
 */
inline fun Email.reply(
    from: String? = null,
    toAll: Boolean = false,
    builder: EmailPopulatingBuilder.() -> Unit
): Email = (if (toAll) EmailBuilder.replyingToAll(this) else EmailBuilder.replyingTo(this))
    .apply { if (from != null) from(from) }.apply(builder).buildEmail()

/**
 * Forward this net.axay.simplekotlinmail.email.
 * This functions opens a new net.axay.simplekotlinmail.email builder, automatically builds
 * the new net.axay.simplekotlinmail.email and returns it.
 * @param from optional from recipient address
 */
inline fun Email.forward(from: String? = null, builder: EmailPopulatingBuilder.() -> Unit): Email =
    EmailBuilder.forwarding(this).apply { if (from != null) from(from) }.apply(builder).buildEmail()
