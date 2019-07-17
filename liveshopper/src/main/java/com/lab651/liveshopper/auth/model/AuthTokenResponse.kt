package com.lab651.liveshopper.auth.model

data class AuthTokenResponse(
    val access_token: String,
    val expires_in: String,
    val refresh_token: String,
    val token_type: String,
    val id_token: String,
    val session_state: String
)