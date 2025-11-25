package com.kidzie.streamview.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModel Module for Dependency Injection
 *
 * Add your ViewModels here using the viewModel DSL
 *
 * Example:
 * viewModel { MovieViewModel(get()) }
 * viewModel { (movieId: Int) -> MovieDetailViewModel(movieId, get()) }
 */
val viewModelModule = module {
    // Add your ViewModels here
    // Example:
    // viewModel { MovieViewModel(get()) }
}

