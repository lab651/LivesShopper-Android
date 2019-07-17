package com.lab651.liveshopper.task.model

data class RewardClaimCount(
    /**
     * How many times the reward has been claimed
     *
     *
     */
    val actual: Long,

    /**
     * Max number of rewards for a campaign
     *
     *
     */
    val max: Long
)
