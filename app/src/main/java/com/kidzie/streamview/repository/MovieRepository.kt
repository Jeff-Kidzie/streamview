package com.kidzie.streamview.repository

import com.kidzie.streamview.core.Result
import com.kidzie.streamview.datasource.model.Movie

interface MovieRepository {
    suspend fun getNowPlayingMovies(count : Int) : Result<List<Movie>>
    suspend fun getPopularMovies()
    suspend fun getTopRatedMovies()
    suspend fun getUpcomingMovies()
    suspend fun getMovieDetail()
}

