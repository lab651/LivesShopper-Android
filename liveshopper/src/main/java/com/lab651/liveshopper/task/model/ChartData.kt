package com.lab651.liveshopper.task.model

data class ChartData(
    /**
     * The name of the value being represented
     *
     * @example: There are 100 "bunnies" in the park
     */
    val name: String,

    /**
     * The name of the structure level the data comes from
     */
    val structureLevelName: String,

    /**
     * The Long of what is represented
     *
     * @example: There are "100" bunnies in the park
     */
    val value: Long = 0

)
