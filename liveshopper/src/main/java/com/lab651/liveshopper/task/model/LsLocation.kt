package com.lab651.liveshopper.task.model

data class LsLocation(
    /**
     * The key associated with a entity
     */
    val key: String = "",

    /**
     * Date/time entity was created
     */
    val created: Long = 0,

    /**
     * Date/time entity was changed
     */
    val modified: Long = 0,

    /**
     * Version of the entity
     */
    val version: Long = 0,

    /**
     * Determines if location is on
     *
     *
     */
    val active: Boolean = true,

    /**
     * The primary address of the location
     *
     *
     */
    val address1: String = "",

    /**
     * Secondary or extension of primary address of location
     *
     *
     */
    val address2: String = "",

    /**
     * City in which the location is
     *
     *
     */
    val city: String = "",

    /**
     * The key that refers to a specific client
     *
     *
     */
    val clientKey: String = "",

    /**
     *  This is used when sending data back to the client for integration with their existing systems
     *
     *
     */
    val clientReference: String? = "",

    /**
     * Country in which location is
     *
     *
     */
    val country: String = "",

    /**
     * keeps a list of emails for client
     *
     *
     */
    val emails: List<String> = arrayListOf(),

    /**
     * The coordinates to determine center of geo fencing
     *
     *
     */
    val geofenceCoords: LsLocationPair? = null,

    /**
     * The distance from center in which geo fencing is working
     *
     *
     */
    val geofenceRadius: Double? = null,

    /**
     * The latitude of the location
     *
     *
     */
    val latitude: String = "0",

    /**
     * The longitude of the location
     *
     *
     */
    val longitude: String = "0",

    /**
     * The name of locaton
     *
     *
     */
    val name: String = "",

    /**
     * Phone number of the location
     *
     *
     */
    val phoneNumber: String? = "",

    /**
     * Owner of the location
     *
     *
     */
    val owners: Owners = Owners(),

    /**
     * The State in which the location is
     *
     *
     */
    val state: String = "",

    /**
     * The structure level of the location
     *
     *
     */
    val structure: List<LsStructureLevelNode> = arrayListOf(),

    /**
     * Zip code of the location
     *
     *
     */
    val zipCode: String = ""
)
