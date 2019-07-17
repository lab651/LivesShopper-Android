package com.lab651.liveshopper.task.model

data class Campaign(

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
     * When does the campaign start?
     *
     *
     */
    val available: Long = 0,

    /**
     * The max distance to claim a task. Leave null if customization isn"t needed.
     *
     *
     */
    val claimDistanceOverride: String,

    /**
     * This is used when sending data back to the client for integration with
     * their existing systems
     *
     *
     */
    val clientReference: String = "",

    /**
     * The Long of completed tasks for this campaign.
     *
     * This is a calculated field that is only used for reporting
     *
     *
     */
    val completedTasksCount: Long = 0,

    /**
     * Flag indicating campaign has been deleted.
     *
     *
     */
    val deleted: Boolean = false,

    /**
     * Text description of the campaign.
     *
     *
     */
    val description: String = "",

    /**
     * When does the campaign conclude?
     *
     *
     */
    val due: Long = 0,

    /**
     * Locations where data can be collected.
     *
     *
     */
    val locations: List<String>,

    /**
     * Who owns this data?
     *
     *
     */
    val owners: Owners,

    /**
     * List of questions defining the data to be collected.
     *
     *
     */
    val questions: List<Question>,

    /**
     * Limits on campaign task completion
     *
     *
     */
    val restrictions: CampaignRestrictions,

    /**
     * Rewards to be offered to users.
     *
     *
     */
    val rewards: List<Reward>,

    /**
     *  State that the campaign is in.
     *
     *  Acceptable values are:
     *      `complete"
     *      `editable"
     *      `error"
     *      `paused"
     *      `running"
     *      `unknownState"
     *      `deleted"
     *
     *
     */
    val state: String = "",

    /**
     * Name that will be given to the campaign.
     *
     *
     */
    val title: String = ""
)
