package com.lab651.liveshopper.auth.model

data class AuthResponse (
    val code: String,
    val state: String
)