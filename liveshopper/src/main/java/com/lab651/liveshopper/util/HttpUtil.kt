package com.lab651.liveshopper.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

/**
 * Utility class for creating http client for use with retrofit2
 */
object HttpUtil {
    var token = ""//""Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlaSI6IjM2MDAwIiwidHQiOiJCZWFyZXIiLCJhdCI6IjZlYjYxNWE5MGU4YTliNzhlODcxIiwiY2lkIjoiLUxYc3l2WjFrblFwVlpETkt6R1IiLCJ1aWQiOiJkY2MyYzcyZDBkZDBhMGJmMzhkNmU0NDY4NGQ1NjFhNiIsInNjb3BlIjoib3BlbmlkIiwiaXNzIjoiaHR0cHM6Ly9pZC1kZXYubGl2ZXNob3BwZXIuY29tIiwicnQiOiI2MTZlYjcxN2ZhNTZiOWI4MzA2NCIsImV4cCI6MTU2Mjc0MDMxMiwiaWF0IjoxNTYyNzA0MzEyLCJhdWQiOiItTFhzeXZaMWtuUXBWWkROS3pHUiIsInN1YiI6ImRjYzJjNzJkMGRkMGEwYmYzOGQ2ZTQ0Njg0ZDU2MWE2In0.gavCPt40CDBOPLPM2g5HgINcZhcXsjNsNO7e1qGC72o"

    /**
     * Builds a new OkHttpClient with a user agent and optional logging
     *
     * @param enableLogging Whether or not logging should occur
     */
    @JvmOverloads
    fun getOkhttpClient(enableLogging: Boolean = false): OkHttpClient {
        return OkHttpClient.Builder().apply {
            callTimeout(2000, TimeUnit.SECONDS)
            connectTimeout(2000, TimeUnit.SECONDS) // connect timeout
            readTimeout(2000, TimeUnit.SECONDS)    // socket timeout
            addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "$token").build()
                chain.proceed(request)
            }

            addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build()
                chain.proceed(request)
            }

            if (enableLogging) {
                addNetworkInterceptor(HttpLoggingInterceptor().apply { level = Level.BODY })
            }
        }.build()
    }
}
