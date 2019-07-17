package com.lab651.liveshopper.task.model

data class TaskResponse(
    /**
     * Key to the next question.
     *
     *
     */
    val nextKey: String? = null,

    /**
     * Who owns this response?
     *
     *
     */
    val owners: Owners? = Owners(),

    /**
     * Url of the uploaded photoUrl
     *
     *
     */
    val photoUrl: String? = null,

    /**
     * Question object
     *
     *
     */
    val question: Question? = null,

    /**
     * The total number of "points" the question has been awarded.
     *
     * Note: This is calculated upon save.
     *
     *
     */
    val score: Int = 0,

    /**
     * Represents the state of the claimedTask.
     *
     * On invalid validation, return invalid and same key.
     *
     *  Acceptable values:
     *     - "completed"
     *     - "continue"
     *     - "invalid"
     *     - "error"
     *     - nknownState"
     *
     *
     *
     */
    val state: String? = null,

    /**
     * Key to the Task.
     *
     *
     */
    val taskKey: String? = null,

    /**
     * User input answer
     *
     * Note: This answer is manually input by the user for question types without predefined answers (Open Ended, rating scale, ...)
     *       Otherwise the array will be empty and the answers field should be referenced.
     *
     *
     *
     */
    var userAnswer: String? = null,

    /**
     * User input image base64 string
     */
    var base64: String? = null,

    /**
     * User selected answer(s)
     *
     * Note: This is an array of answer keys for question types that have predefined answers (multiple choice, single response)
     *       Otherwise the array will be empty and the userAnswers field should be referenced
     *
     *
     */
    val answers: ArrayList<String> = arrayListOf()

) : Entity()