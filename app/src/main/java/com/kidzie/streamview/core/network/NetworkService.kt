package com.kidzie.streamview.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {
    private const val BASE_URL = "https://api.themoviedb.org/3/" // Replace with your API URL
    //TODO : Move this api key to more secure file
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMGQxY2IwOGI3YjU3ODgyYWRhYTczMWZiMWJkNmI5ZiIsIm5iZiI6MTc1NzE0NjY5NS45NjgsInN1YiI6IjY4YmJlZTQ3ZjRmYzc0NGU1MTk4YjU2OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dCQQdo20p9eWcBEjkp6dOWmmSpKtqJPBvZ-QV1-Wtek" // Replace with your API key

    private const val TIMEOUT_SECONDS = 30L

    /**
     * Logging interceptor for debugging network requests
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Auth interceptor to add API key or token to requests
     */
    private val authInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $API_KEY")
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }

    /**
     * OkHttp client configuration
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    /**
     * Retrofit instance
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Create API service instance
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    /**
     * Inline reified version for easier usage
     */
    inline fun <reified T> create(): T {
        return createService(T::class.java)
    }
}