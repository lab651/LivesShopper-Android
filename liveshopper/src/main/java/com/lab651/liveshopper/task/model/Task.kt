package com.lab651.liveshopper.task.model

import java.util.*

data class Task (
    /**
     * Internal unique identifier
     */
    private var uuid: String? = null,

    /**
     * The max distance to claim a claimedTask. Leave null if customization isn"t needed.
     *
     *
     */
    val claimDistanceOverride: String?,

    /**
     * When was this claimedTask completed?
     *
     *
     */
    val completed: Long?,

    /**
     * Description of the claimedTask, e.g. instructions
     *
     *
     */
    val description: String = "",

    /**
     * Distance from the current user.
     *
     *
     */
    val distance: Double = 0.0,

    /**
     * Represents when this claimedTask must be completed by.
     *
     *
     */
    val due: Long = 0,

    /**
     * SQL identifier of the claimedTask
     *
     *
     */
    val id: Long = 0,

    /**
     * Details about the location where this claimedTask can be completed.
     *
     *
     */
    val location: Address,

    /**
     * Location key
     *
     *
     */
    val locationKey: String,

    /**
     * Company logo
     *
     *
     */
    val logo: String?,

    /**
     * Indicates that the claimedTask is only for users defined by the client
     */
    val associatesOnly: Boolean?,

    /**
     * Key to the next question
     *
     * This will be a pointer to the first question on load.
     *
     *
     */
    val nextQuestion: String? = null,

    /**
     * Who owns this claimedTask?
     *
     *
     */
    val owners: Owners?,

    /**
     * Questions in this claimedTask.
     *
     *
     */
    val questions: List<Question>,

    /**
     * What are the rewards?
     *
     *
     */
    val rewards: List<Reward>,

    /**
     *   Represents the current state of the claimedTask.
     *
     *   This will progressively be updated when responses are sent via POST.
     *
     *   Acceptable values:
     *       - "running"
     *       - "completed"
     *       - "cancelled"
     *       - "error"
     *       - "rejected"
     *       - "unknownState"
     *
     *
     *
     */
    val state: String = "",

    /**
     * Represents an estimated time required to complete this claimedTask.
     *
     *
     */
    val time: EstimatedTime?,

    /**
     * "Name" of the claimedTask.
     *
     *
     */
    val title: String = "",
    /**
     * List of requirements for a given claimedTask
     */
    val taskRequirements: List<String>

) : Entity() {

    fun getId(): String {

        if (uuid == null) {
            uuid = UUID.randomUUID().toString()
        }
        return uuid as String
    }
}