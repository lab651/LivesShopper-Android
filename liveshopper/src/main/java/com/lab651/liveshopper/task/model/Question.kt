package com.lab651.liveshopper.task.model

import android.media.Image

data class Question(

    /**
     * The randomization option for multiple choice and multi-select questions.
     * If not specified, this option defaults to randomize.
     *
     * Acceptable values are:
     *     - "randomize"
     *     - "randomlyReverse"
     *     - "sorted"
     *
     *
     *
     */
    val answerOrder: String = "randomize",

    /**
     * List of possible answers
     *
     *
     */
    val answers: List<Answer>,

    /**
     * This is used when sending data back to the client for integration with
     * heir existing systems
     *
     *
     */
    val clientReference: String?,

    /**
     * List of critical answers.
     * If a critical answer is selected/entered a notification is sent to the appropriate party.
     *
     *
     */
    val criticalAnswers: List<String>,

    /**
     * Option to allow open-ended text box for Single Answer and Multiple Answer question types.
     *
     * This can be used with SINGLE_ANSWER, SINGLE_ANSWER_WITH_IMAGE, MULTIPLE_ANSWERS, and
     * MULTIPLE_ANSWERS_WITH_IMAGE question types.
     *
     *
     */
    val hasOther: Boolean?,

    /**
     * For rating questions, the text for the higher end of the scale, such as "Best".
     * For numeric questions, a String representing a floating-point that is the maximum allowed number for a response.
     *
     *
     */
    val highValueLabel: String?,

    /**
     * Collection of images associated with this question.
     *
     *
     */
    val images: List<Image>,

    /**
     * Indicates that the user is not required to answer the question.
     *
     *
     */
    val isOptional: Boolean?,

    /**
     * Indicates wether or not the answer can contain decimal.
     *
     *
     */
    val allowDecimals: Boolean?,

    /**
     * Allows the final answer in the collection to be pinned
     * to the end of the process.
     *
     *
     */
    val lastAnswerPositionPinned: Boolean?,

    /**
     * For rating questions, the text for the lower end of the scale, such as "Worst".
     * For numeric questions, a String representing a floating-point that is the minimum allowed number for a response.
     *
     *
     */
    val lowValueLabel: String?,

    /**
     * The max number of answers the user can choose for multiple answers
     *
     *
     */
    val maxMultipleAnswers: Long?,

    /**
     * The highest possible number of "points" the question can be awarded.
     *
     *
     */
    val maxScore: Long?,

    /**
     * The minimum number of answers the user can choose for multiple answers
     *
     *
     */
    val minMultipleAnswers: Long?,

    /**
     * Option to force the user to pick one of the open text suggestions.
     * This requires that suggestions are provided for this question.
     *
     *
     */
    val mustPickSuggestion: Boolean?,

    /**
     * Number of stars to use for ratings questions.
     *
     * Acceptable values are:
     * - 11
     * - 5
     * - 7
     * - 10
     *
     *
     *
     */
    val numStars: Int = 0,

    /**
     * Placeholder text for an open text question.
     *
     *
     */
    val openTextPlaceholder: String?,

    /**
     * A list of suggested answers for open text question auto-complete.
     * This is only valid if single_line_response is true.
     *
     *
     */
    val openTextSuggestions: List<String>,

    /**
     * The order in which this question appears by default in the task.
     *
     *
     */
    val order: Int?,

    /**
     * Who owns this data?
     *
     *
     */
    val owners: Owners?,

    /**
     * Key of the question stored in the campaign
     *
     *
     */
    val parentKey: String?,

    /**
     * Options to determine if and how to capture a photo for the question
     *
     *
     */
    val photoCaptureOptions: PhotoCaptureOptions?,

    /**
     * Pointers to create linked list
     *
     *
     */
    val pointers: KeyedListPointers?,

    /**
     * Required question text shown to the respondent.
     *
     *
     */
    val question: String = "",

    /**
     * How many responses are desired?
     *
     *
     */
    val responseCount: TaskResponseCount?,

    /**
     * Defines the rating interval for numeric answers
     *
     *
     */
    val scoreIntervals: List<ScoreInterval>,

    /**
     * Used by the Rating Scale with Text question type.
     *
     * This text goes along with the question field that is presented to the respondent,
     * and is the actual text that the respondent is asked to rate.
     *
     *
     */
    val sentimentText: String?,

    /**
     * Option to allow multiple line open text responses instead of a single line response.
     * Note that we don"t show auto-complete suggestions with multiple line responses.
     *
     *
     */
    val singleLineResponse: Boolean?,

    /**
     * The threshold/screener answer options, which will screen a user into the rest of the survey.
     * These will be a subset of the answer option Strings.
     *
     *
     */
    val thresholdAnswers: List<String>,

    /**
     * Required field defining the question type.
     *
     * Acceptable values are:
     *    - "multipleAnswers"
     *    - "numericOpenEnded"
     *    - "openEnded"
     *    - "ratingScale"
     *    - "ratingScaleWithText"
     *    - "singleAnswer"
     *    - "barcodeScan"
     *
     * Not implemented:
     *    - "largeImageChoice"
     *    - "menuWithImage"
     *    - "multipleAnswersWithImage"
     *    - "openEndedWithImage"
     *    - "ratingScaleWithImage"
     *    - "sideBySideImages"
     *    - "singleAnswerWithImage"
     *    - "video"
     */
    val type: String = "undefined",
    /**
     * Optional unit of measurement for display (for example: hours, people, miles).
     */
    val unitOfMeasurementLabel: String?,

    /**
     * Location to display the unit of measurement. (for example: $1 vs 1 puppy)
     *
     */
    val showUnitsOnLeft: Boolean = false,

    /**
     * The YouTube video ID to be shown in video questions.
     */
    val videoId: String?
)
