package com.lab651.liveshopper.task.model

class Reward(
    /**
     * Number of days before the reward is eligible for activation. (Fraud prevention)
     *
     *
     */
    val activationDelay: Long?,

    /**
     * Number of days from activation until expiration
     *
     *
     */
    val activationExpiration: Long?,

    /**
     * The unit to measure activationExpiration value by.
     */
    val activationExpiryPeriodType: Long,

    /**
     * Barcode used to claim reward
     */
    val barcode: RewardBarcode?,

    /**
     * The key of the reward pushed to the campaign (firebase: /campaign/:key:/rewards/). Used with claimed-rewards
     */
    val campaignRewardKey: String,

    /**
     * Number of times a reward has been claimed
     */
    val claimCount: RewardClaimCount?,

    /**
     * Name of the client who owns campaign
     */
    val clientName: String?,

    /**
     * This is used when sending data back to the client for integration with
     * their existing systems
     */
    val clientReference: String?,

    /**
     * Technical legal terms referring to how rewards will work
     *
     * @example: terms and conditions of a contract
     */
    val legalese: String?,

    /**
     * Logo for the reward
     *
     *
     */
    val logo: String?,

    /**
     * Max Long of times one reward can be redeemed
     */
    val maxRedemptions: Long?,

    /**
     * Message of the reward
     * @example: $5 off your next meal
     *
     *
     */
    val message: RewardMessage?,

    /**
     * Owners of the reward
     *
     *
     */
    val owners: Owners?,

    /**
     * The key of the original reward created by the client (firebase: /rewards/)
     *
     *
     */
    val parentKey: String?,

    /**
     * Number of days from creation until expiration
     *
     *
     */
    val redemptionExpiration: Long?,

    /**
     * This text will display on social media when a reward is shared from the mobile app
     *
     */
    val shareText: String?
)
