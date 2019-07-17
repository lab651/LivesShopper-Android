package com.lab651.liveshopper.auth

import com.google.gson.GsonBuilder
import com.lab651.liveshopper.util.HttpUtil
import com.lab651.liveshopper.auth.model.AuthResponse
import com.lab651.liveshopper.auth.model.AuthTokenBody
import com.lab651.liveshopper.auth.model.AuthTokenResponse
import net.openid.appauth.CodeVerifierUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Authentication Client
 */
object AuthClient {

    private val service by lazy { createService() }

    private val BASE_URL_DEV = "https://id-dev.liveshopper.com/"
    private val BASE_URL_PROD = "https://id.liveshopper.com/"
    private val DEFAULT_RESPONSE_TYPE = "code"
    private val DEFAULT_SCOPE = "openid"

    private fun createService(): AuthService {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_DEV)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(HttpUtil.getOkhttpClient(true))
            .build()

        return retrofit.create(AuthService::class.java)
    }

    /**
     * Authorize a user.
     *
     * @param user_id
     * @param response_type
     * @param state
     * @param scope
     * @param code_challenge
     * @param redirect_uri
     * @param code_challenge_method
     * @param client_id
     */
    @JvmOverloads
    @JvmStatic
    fun authorizeUser(
        code_challenge: String,
        user_id: String,
        client_id: String,
        redirect_uri: String,
        state: String,
        onResponse: (AuthResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {},
        response_type: String = DEFAULT_RESPONSE_TYPE,
        scope: String = DEFAULT_SCOPE,
        code_challenge_method: String = CodeVerifierUtil.getCodeVerifierChallengeMethod()
    ) {
        service.authorizeUser(
            user_id,
            response_type,
            state,
            scope,
            code_challenge,
            redirect_uri,
            code_challenge_method,
            client_id
        )
            .enqueue(ApiCallback<AuthResponse>(onResponse, onFailure))
    }

    /**
     * Retrieve an auth token.
     *
     * @param requestBody
     * @param response_type
     * @param state
     **/
    @JvmOverloads
    @JvmStatic
    fun getToken(
        requestBody: AuthTokenBody,
        onResponse: (AuthTokenResponse) -> Unit,
        onFailure: (Throwable) -> Unit = {}
    ) {
        service.getAuthToken(requestBody).enqueue(ApiCallback<AuthTokenResponse>(onResponse, onFailure))
    }

    private class ApiCallback<T>(
        val onResponse: (T) -> Unit,
        val onFailure: (Throwable) -> Unit
    ) : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                onResponse(it)
            } ?: onFailure(Exception("Response body is null"))
        }
    }

}