package com.lab651.liveshopper.task.model

data class User(
    val accountEnabled: Boolean,
    val profile: Profile,
    val state: String,
    val email: String,
    val roles: String,
    val version: Int,
    val authId: String,
    val cid: String,
    val clientReference: String,
    val deviceUuid: String,
    val managedNodes: String?,
    val phoneNumber: String?,
    val pushToken: String?,
    val uid: String,
    val username: String,
    val key: String,
    val created: Int,
    val modified: Int
) {
    data class Profile(
        val name: String,
        val title: String,
        val language: String,
        val enabledSmsNotifications: Boolean,
        val enabledEmailNotifications: Boolean
    )
}

