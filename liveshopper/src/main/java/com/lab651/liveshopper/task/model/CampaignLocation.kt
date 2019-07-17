package com.lab651.liveshopper.task.model

data class CampaignLocation(

    /**
     * Key used to specify a campaign
     *
     *
     */
    val campaignKey: String = "",

    /**
     * Key used to specify the location of the campaign
     *
     *
     */
    val locationKey: String = "",

    /**
     * Latitude to determine the campaigns location
     *
     *
     */
    val latitude: String = "0",

    /**
     * Longitude to determine the campaigns location
     *
     *
     */
    val longitude: String = "0"
)
