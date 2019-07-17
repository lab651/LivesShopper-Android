package com.lab651.liveshopper.task.model

data class Address(
    /**
     * Primary address
     *
     *
     */
    val address1: String = "",

    /**
     * The secondary or extension of primary address
     *
     *
     */
    val address2: String = "",

    /**
     * City of the address
     *
     *
     */
    val city: String = "",

    /**
     * Country of the address
     *
     *
     */
    val country: String = "",

    /**
     * Coordinates for address
     *
     *
     */
    val geofenceCoords: LsLocationPair?,

    /**
     * Approximate distance from address
     *
     *
     */
    val geofenceRadius: Number,

    /**
     * Name of address
     *
     *
     */
    val name: String = "",

    /**
     * Phone Number for the address
     *
     *
     */
    val phoneNumber: String = "",

    /**
     * State for the address
     *
     *
     */
    val state: String = "",

    /**
     * Zip code of the address
     *
     *
     */
    val zipCode: String = "",

    /**
     * Locations latitude
     *
     *
     */
    val latitude: Double,

    /**
     * Locations longitude
     *
     *
     */
    val longitude: Double
)
