package com.lab651.liveshopper.task.model

data class TaskResponseCount(
    /**
     * How many users have responded to a task
     *
     *
     */
    val actual: Number = 0,

    /**
     * How many users are wanted to respond to a task
     *
     *
     */
    val wanted: Number = 0
)
