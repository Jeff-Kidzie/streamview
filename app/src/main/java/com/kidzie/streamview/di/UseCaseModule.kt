package com.kidzie.streamview.di

import com.kidzie.streamview.repository.MovieRepository
import com.kidzie.streamview.usecase.GetNowPlayingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNowPlayingUseCase(get<MovieRepository>()) }
}