package com.kidzie.streamview.usecase

import com.kidzie.streamview.core.Result
import com.kidzie.streamview.datasource.model.Movie
import com.kidzie.streamview.repository.MovieRepository

class GetNowPlayingUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(count : Int = 3): Result<List<Movie>> {
        return movieRepository.getNowPlayingMovies(count)
    }
}