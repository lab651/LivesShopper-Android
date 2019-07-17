package com.lab651.liveshopper.task.model

data class CampaignRestrictions(
    /**
     * The key associated with a entity
     */
    val key: String,

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
     * Optional maximum Long of total completions
     *
     *
     */
    val maxLimit: Long,

    /**
     * Time period value to limit acceptances per location
     *
     * Used in conjuction with`locationAcceptanceLimitTimePeriodType`
     *
     *
     */
    val locationAcceptanceLimitTimePeriod: Long,

    /**
     * Time period type to limit acceptances per location
     *
     *
     */
    val locationAcceptanceLimitTimePeriodType: Int,

    /**
     * Maximum Long of completions per location.
     *
     * Used in conjuction with `locationLimitTimePeriod` and `locationLimitTimePeriodType`
     *
     *
     */
    val locationLimit: Long,

    /**
     * Time period value to limit completions per location
     *
     *
     */
    val locationLimitTimePeriod: Long,

    /**
     * Time period type to limit completions per location
     *
     *
     */
    val locationLimitTimePeriodType: Int,

    /**
     * Campaign, Client, and/or User
     *
     *
     */
    val owners: Owners,

    /**
     * Maximum Long of completions per structure level
     *
     * Used in conjuction with `structureLimitLevel` and `structureLimitTimePeriod`
     *
     *
     */
    val structureLimit: Long,

    /**
     * Structure Level to limit completions per structure level
     *
     *
     */
    val structureLimitLevel: String,

    /**
     * Time period value to limit completions per structure level
     *
     *
     */
    val structureLimitTimePeriod: Long,

    /**
     * Time period type to limit completions per structure level
     *
     *
     */
    val structureLimitTimePeriodType: Int,

    /**
     * Maximum Long of completions per user
     *
     * Used in conjuction with `userLimitTimePeriod`
     *
     *
     */
    val userLimit: Long,

    /**
     * Time period value to limit completions per user
     *
     *
     */
    val userLimitTimePeriod: Long,

    /**
     * Time period type to limit completions per user
     *
     *
     */
    val userLimitTimePeriodType: Int,

    /**
     * Maximum Long of completions per user, per location
     *
     * Used in conjuction with `userLocationLimitTimePeriod`
     *
     *
     */
    val userLocationLimit: Long,

    /**
     * Time period value to limit completions per user, per location
     *
     *
     */
    val userLocationLimitTimePeriod: Long,

    /**
     * Time period type to limit completions per user, per location
     *
     *
     */
    val userLocationLimitTimePeriodType: Int,

    /**
     * Maximum Long of completions per user, per structure level
     *
     * Used in conjuction with `userStructureLimitLevel` and `userStructureLimitTimePeriod`
     *
     *
     */
    val userStructureLimit: Long,

    /**
     * Structure Level to limit completions per user, per structure level
     *
     *
     */
    val userStructureLimitLevel: String,

    /**
     * Time period value to limit completions per user, per structure level
     *
     *
     */
    val userStructureLimitTimePeriod: Long,

    /**
     * Time period type to limit completions per user, per structure level
     *
     *
     */
    val userStructureLimitTimePeriodType: Int,

    /**
     * Time period value to limit how long a user has to complete a task
     *
     *
     */
    val userTaskExpirationPeriod: Long,

    /**
     * Time period type to limit how long a user has to complete a task
     *
     *
     */
    val userTaskExpirationPeriodType: Int
)
