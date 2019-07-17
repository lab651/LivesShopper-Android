package com.lab651.liveshopper.auth

import com.lab651.liveshopper.auth.model.AuthResponse
import com.lab651.liveshopper.auth.model.AuthTokenBody
import com.lab651.liveshopper.auth.model.AuthTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    /**
     * Authorize a user
     *
     * @param userId
     * @param responseType
     * @param state
     * @param scope
     * @param codeChallenge
     * @param redirectUri
     * @param codeChallengeMethod
     * @param clientId
     */
    @GET("authorize")
    fun authorizeUser(
        @Query("user_id") userId: String,
        @Query("response_type") responseType: String,
        @Query("state") state: String,
        @Query("scope") scope: String,
        @Query("code_challenge") codeChallenge: String,
        @Query("redirect_uri", encoded = true) redirectUri: String,
        @Query("code_challenge_method") codeChallengeMethod: String,
        @Query("client_id") clientId: String
    ): Call<AuthResponse>

    /**
     * Get an auth token
     *
     * @param body The body of the request
     */
    @POST("token")
    fun getAuthToken(
        @Body body: AuthTokenBody
    ): Call<AuthTokenResponse>

}