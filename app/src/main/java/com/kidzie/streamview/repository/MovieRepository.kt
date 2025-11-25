package com.kidzie.streamview.repository

interface MovieRepository {
    suspend fun getNowPlayingMovies()
    suspend fun getPopularMovies()
    suspend fun getTopRatedMovies()
    suspend fun getUpcomingMovies()
    suspend fun getMovieDetail()
}

