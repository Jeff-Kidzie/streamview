package com.kidzie.streamview.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    // Base URL
    single { provideBaseUrl() }

    // API Key
    single { provideApiKey() }

    // Logging Interceptor
    single { provideLoggingInterceptor() }

    // Auth Interceptor
    single { provideAuthInterceptor(get()) }

    // OkHttp Client
    single { provideOkHttpClient(get(), get()) }

    // Retrofit
    single { provideRetrofit(get(), "https://api.themoviedb.org/3/") }
}

private fun provideBaseUrl(): String {
    return "https://api.themoviedb.org/3/"
}

private fun provideApiKey(): String {
    //TODO : Move this api key to more secure file
    return "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMGQxY2IwOGI3YjU3ODgyYWRhYTczMWZiMWJkNmI5ZiIsIm5iZiI6MTc1NzE0NjY5NS45NjgsInN1YiI6IjY4YmJlZTQ3ZjRmYzc0NGU1MTk4YjU2OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dCQQdo20p9eWcBEjkp6dOWmmSpKtqJPBvZ-QV1-Wtek"
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private fun provideAuthInterceptor(apiKey: String): Interceptor {
    return Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    authInterceptor: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .writeTimeout(30L, TimeUnit.SECONDS)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

