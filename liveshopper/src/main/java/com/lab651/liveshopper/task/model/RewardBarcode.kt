package com.lab651.liveshopper.task.model

data class RewardBarcode(
    /**
     * A human readable equivalent of the barcode value, used when the barcode cannot be scanned.
     *
     *
     */
    val alternateText: String?,

    /**
     * Directions on how and where to use this barcode
     *
     *
     */
    val instructions: String?,

    /**
     * The corresponding client reward
     *
     *
     */
    val rewardKey: String?,

    /**
     *   State that the campaign is in.
     *
     *        Acceptable values are:
     *            "available"
     *            "issued"
     *            "redeemed"
     *            "unknownState"
     *
     *
     *
     */
    val state: String,

    /**
     *   Possible Types:
     *       "aztec"
     *       "codabar"
     *       "code128"
     *       "code39"
     *       "dataMatrix"
     *       "ean13"
     *       "ean8"
     *       "itf14"
     *       "pdf417"
     *       "pdf417Compact"
     *       "qrCode"
     *       "textOnly"
     *       "upcA"
     *       "upcE"
     *
     *
     *
     */

    val type: String?,

    /**
     * Used to determine wether or not a barcode is unique
     *
     *
     */
    val isUnique: Boolean?,

    /**
     * The value encoded in the barcode.
     *
     *
     */
    val value: String?
)
