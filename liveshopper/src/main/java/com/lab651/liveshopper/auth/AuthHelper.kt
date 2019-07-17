package com.lab651.liveshopper.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.lab651.liveshopper.BuildConfig
import com.lab651.liveshopper.R
import com.lab651.liveshopper.auth.model.AuthResponse
import com.lab651.liveshopper.auth.model.AuthTokenBody
import com.lab651.liveshopper.auth.model.AuthTokenResponse
import com.lab651.liveshopper.util.HttpUtil
import net.openid.appauth.CodeVerifierUtil
import java.util.*
import kotlin.collections.HashMap


/**
 * A helper class to handle obtaining an auth token for a user
 *
 * @param context used to gain access to shared preferences where the token is stored
 */
internal class AuthHelper(
    val context: Context,
    private val userID: String,
    private val clientID: String,
    private val redirectURL: String
) {

    private val codeVerifier: String by lazy { CodeVerifierUtil.generateRandomCodeVerifier() }

    /**
     * Attempts to authorize a user and obtain a bearer token if one does not exist.
     *
     * @param completionSuccess called when a valid token is found or when a new auth token is
     * obtained via the refresh token.
     * @param completionFailure called when authentication does not succeed.
     */
    fun authenticate(completionSuccess: () -> Unit, completionFailure: (Throwable) -> Unit) {
        // Check the shared prefs for a non expired token
        alreadyAuthenticated(completionSuccess, {
            // Token is expired and refresh token authentication failed.
            val codeChallenge = CodeVerifierUtil.deriveCodeVerifierChallenge(codeVerifier)
            val state = UUID.randomUUID().toString()

            Toast.makeText(context, "Logging in", Toast.LENGTH_LONG).show()

            // Call to the auth API
            AuthClient.authorizeUser(
                codeChallenge,
                userID,
                clientID,
                redirectURL,
                state,
                {
                    Log.d(TAG, "Response is ${it.code}")

                    // The state UUID created above must match the response state to continue
                    if (state == it.state) {
                        getAuthToken(it, completionSuccess)
                    } else {
                        // There was an error verifying the session state
                        val error = Throwable("There was an error verifying the session state.")
                        Log.e(TAG, error.message)
                        if (BuildConfig.DEBUG) Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                        completionFailure(error)
                    }
                }, {
                    val message = "Authorization failed!"
                    Log.e(TAG, message, it)
                    if (BuildConfig.DEBUG) Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    completionFailure(it)
                })
        })
    }


    /**
     * Calls the auth API to obtain a token
     *
     * @param authResponse the response model that returned from the initial auth API call.
     * @param successCallback called when a valid token is found or when a new auth token is
     * obtained via the refresh token.
     */
    private fun getAuthToken(authResponse: AuthResponse, successCallback: () -> Unit) {
        // Use the code from the response and the code verifier from above to get a token using the AuthAPI
        AuthClient.getToken(
            AuthTokenBody(
                client_id = clientID,
                code = authResponse.code,
                code_verifier = codeVerifier,
                redirect_uri = redirectURL,
                grant_type = GRANT_TYPE_AUTHORIZATION
            ),
            { authTokenResponse ->

                // Set the auth token on the http client to be used for all future API requests
                HttpUtil.token = authTokenResponse.token_type + " " + authTokenResponse.access_token
                storeAuthInfo(authTokenResponse)

                // After success finish setting up the app
                successCallback()
                if (BuildConfig.DEBUG) Toast.makeText(context, "User authorization success!", Toast.LENGTH_LONG).show()
            },
            { throwable ->
                val message = "Token generation failed!"
                Log.e(TAG, message, throwable)
                if (BuildConfig.DEBUG)  Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            })
    }

    /**
     * Store auth info to preferences
     */
    private fun storeAuthInfo(authTokenResponse: AuthTokenResponse) {
        // Storing tokens to user prefs
        val currentTime = System.currentTimeMillis()

        val sharedPrefMap = HashMap<String, String>(4)
        sharedPrefMap[context.getString(R.string.authToken)] = HttpUtil.token
        sharedPrefMap[context.getString(R.string.refreshToken)] = authTokenResponse.refresh_token
        sharedPrefMap[context.getString(R.string.tokenCreationTime)] = currentTime.toString()
        sharedPrefMap[context.getString(R.string.tokenExpirationTime)] = authTokenResponse.expires_in

        saveToSharedPrefs(sharedPrefMap)
    }

    /**
     * Checks whether or not there is a valid token, if not it checks for a refresh token and will make a
     * request for a new auth token.
     *
     * @param successCallback called when a valid token is found or when a new auth token is
     * obtained via the refresh token.
     */
    private fun alreadyAuthenticated(successCallback: () -> Unit, failureCallBack: () -> Unit) {
        val authToken = getStoredPref(R.string.authToken)
        val tokenCreationTime = getStoredPref(R.string.tokenCreationTime)
        val tokenExpirationTime = getStoredPref(R.string.tokenExpirationTime)

        authToken?.let {
            // Check to see if the token has expired, if so use the refresh token to generate a new token
            val currentTime = System.currentTimeMillis()

            if (!tokenCreationTime.isNullOrEmpty() && !tokenExpirationTime.isNullOrEmpty()) {
                val timeUntilExpiration = tokenCreationTime.toLong() + (tokenExpirationTime.toLong() * 1000)

                if (timeUntilExpiration > currentTime) {
                    // Token is still valid, need to set it on Http client for use on all future requests
                    HttpUtil.token = authToken
                    successCallback()
                    if (BuildConfig.DEBUG)  Toast.makeText(context, "User authorization success!", Toast.LENGTH_LONG).show()

                    successCallback()
                    return
                }

                val refreshToken = getStoredPref(R.string.refreshToken)

                refreshToken?.let {
                    AuthClient.getToken(
                        AuthTokenBody(
                            client_id = clientID,
                            refresh_token = refreshToken,
                            grant_type = GRANT_TYPE_REFRESH
                        ),
                        { authTokenResponse ->
                            HttpUtil.token = authTokenResponse.token_type + " " + authTokenResponse.access_token
                            storeAuthInfo(authTokenResponse)
                            successCallback()
                            if (BuildConfig.DEBUG) Toast.makeText(context, "User authorization success!", Toast.LENGTH_LONG).show()
                        }, {
                            failureCallBack()
                        })

                } ?: failureCallBack()
            }
        } ?: failureCallBack()
    }

    /**
     * Gets a stored preference based on the string resource id.
     */
    private fun getStoredPref(id: Int): String? {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(context.getString(id), null)
    }

    /**
     * Helper method to save the auth information to preferences
     */
    private fun saveToSharedPrefs(map: java.util.HashMap<String, String>) {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE) ?: return

        with(sharedPref.edit()) {
            val authTokenKey = context.getString(R.string.authToken)
            val refreshTokenKey = context.getString(R.string.refreshToken)
            val tokenCreationTimeKey = context.getString(R.string.tokenCreationTime)
            val tokenExpirationTimeKey = context.getString(R.string.tokenExpirationTime)

            putString(authTokenKey, map[authTokenKey])
            putString(refreshTokenKey, map[refreshTokenKey])
            putString(tokenCreationTimeKey, map[tokenCreationTimeKey])
            putString(tokenExpirationTimeKey, map[tokenExpirationTimeKey])
            apply()
        }
    }

    companion object {
        const val TAG = "LiveShopperAPI"
        const val GRANT_TYPE_AUTHORIZATION = "authorization_code"
        const val GRANT_TYPE_REFRESH = "refresh_token"
        const val SHARED_PREF_KEY = "liveshopper_prefs"
    }

}
