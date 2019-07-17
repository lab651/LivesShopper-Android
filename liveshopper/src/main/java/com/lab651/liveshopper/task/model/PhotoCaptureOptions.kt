package com.lab651.liveshopper.task.model

data class PhotoCaptureOptions(
    /**
     * Firebase URL of image to display to the app user as an example of the photo capture
     *
     *
     */
    val exampleImage: String,

    /**
     * The text to display to the user when prompted to take a photo.
     * e.g. "Take a photo of the bathroom."
     *
     *
     */
    val photoPromptText: String,

    /**
     * Option to force the user to provide a photo for this answer.
     *
     *
     */
    val mustProvidePhoto: Boolean = false,

    /**
     * Photo Overlay: the type of overlay to show over the camera while captureing an image
     *  Options: none, circle, circle_square, rectangle_horizontal, rectangle_vertical, square
     *
     *
     */
    val photoOverlayType: String = "none",

    /**
     * Photo Level: the type of leveling to require when capturing a photo
     * Options: none, flat, vertical
     *
     *
     */
    val photoLevelType: String = "none",

    /**
     * The list of vision options to be validated when saving an image
     *
     *
     */
    val visionOptions: List<VisionOption>,

    /**
     * Used to determine default flash state of user camera
     *
     *
     */
    val defaultFlashState: Boolean = false,

    /**
     * Used to determine if user can toggle flash
     *
     *
     */
    val allowFlashToggle: Boolean = true
)
