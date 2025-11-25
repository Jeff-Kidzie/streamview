package com.kidzie.streamview.di

import com.kidzie.streamview.datasource.remote.MovieService
import com.kidzie.streamview.datasource.remote.RemoteDataSource
import com.kidzie.streamview.datasource.remote.RemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataSourceModule = module {
    // Movie Service API
    single { provideMovieService(get()) }

    // Remote Data Source
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

private fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
}

