package com.lab651.liveshopper.task.model

data class Associate(
    /**
     * Is the associate active or not
     *
     *
     */
    val active: Boolean = true,

    /**
     * Key referring to specific client
     *
     *
     */
    val clientKey: String,

    /**
     * First name of the associate
     *
     *
     */
    val firstName: String = "",

    /**
     * Last name of the associate
     *
     *
     */
    val lastName: String = "",

    /**
     * The employee number. Used to validate a reward
     *
     *
     */

    val number: String = "",

    /**
     * Owner of the associate
     *
     *
     */
    val owners: Owners

)
