package com.lab651.liveshopper.task.model

data class Answer(
    /**
     * The order to present this answer to the user in respect to the other answers
     *
     *
     */
    val answerOrder: Long,

    /**
     * Client defined value for internal referencing
     *
     *
     */
    val clientReference: String,

    /**
     * The text to display to the user in the mobile app.
     *
     *
     */
    val displayText: String,

    /**
     * FUTURE: Utilize the displayTextKey to determine what String to display to the user based upon their language preferences
     *
     *
     */
    val displayTextKey: String,

    /**
     * The key of the question to branch to next if this answer is selected
     *
     *
     */
    val nextQuestionkey: String,

    /**
     * Options to determine if and how to capture a photo for the answer
     *
     *
     */
    val photoCaptureOptions: PhotoCaptureOptions,

    /**
     * The question that this answer is attached to
     *
     *
     */
    val questionKey: String,

    /**
     * Client defined value to be used in scoring
     *
     *
     */
    val score: Long
) : Entity()
