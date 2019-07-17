package com.lab651.liveshopper.task.model

data class VisionOption(
    /**
     * What value are we searching for in the image?
     */
    val keyword: String,

    /**
     * The percentage needed to verify the keyword is present within the image
     */
    val threshold: Long
)
