package com.kidzie.streamview.di

import com.kidzie.streamview.repository.MovieRepository
import com.kidzie.streamview.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    // Movie Repository
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

