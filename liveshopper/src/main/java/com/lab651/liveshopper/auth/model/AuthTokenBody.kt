package com.lab651.liveshopper.auth.model

data class AuthTokenBody(
    val client_id: String,
    val redirect_uri: String = "",
    val grant_type: String,
    val code: String = "",
    val code_verifier: String ="",
    val refresh_token: String = ""
)