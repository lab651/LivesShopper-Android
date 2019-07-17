package com.lab651.liveshopper.task.model

data class EstimatedTime(
    /**
     * Maximum time to do a task
     *
     *
     */
    val max: Long = 5,

    /**
     * Minimum time to do a task
     *
     *
     */
    val min: Long = 3
)
