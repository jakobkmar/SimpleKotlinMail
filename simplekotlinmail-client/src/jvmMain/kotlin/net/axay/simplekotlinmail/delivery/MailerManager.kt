package net.axay.simplekotlinmail.delivery

import org.simplejavamail.api.mailer.Mailer

object MailerManager {

    private val LOCAL_MAILER by lazy { mailerBuilder("localhost", 25) }

    private var DEFAULT_MAILER: Mailer? = null

    /**
     * The default mailer instance, that is used if
     * the no other instance is provided.
     */
    var defaultMailer: Mailer
        get() = DEFAULT_MAILER ?: LOCAL_MAILER
        set(value) {
            DEFAULT_MAILER = value
        }

    private val registeredMailers = HashSet<Mailer>()

    /**
     * Register this mailer instance.
     *
     * Note that mailer instances built with the mailerBuilder
     * are registered automatically.
     */
    fun registerMailer(mailer: Mailer) = registeredMailers.add(mailer)

    /**
     * Shutdown all registered mailers.
     */
    fun shutdownMailers() {
        registeredMailers.removeAll {
            it.shutdownConnectionPool()
            true
        }
    }

}
