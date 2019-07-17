package com.lab651.liveshopper

enum class LiveShopperError(val message: String) {
    SDK_NOT_INITIALIZED("Sdk had not been initialized"),
    AUTH_STATE_MISMATCH("State does not match response")
}