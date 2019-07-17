package com.lab651.liveshopper.task.model

data class ScoreInterval(
    /**
     * How low the score can be before client is notified
     *
     *
     */
    val lowerBound: Long?,

    /**
     * How high a score can be before client is notified
     *
     *
     */
    val upperBound: Long?,

    /**
     * The score for a location
     *
     *
     */
    val score: Long
)
