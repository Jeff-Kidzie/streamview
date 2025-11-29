package com.kidzie.streamview.repository

import com.kidzie.streamview.core.Result
import com.kidzie.streamview.datasource.model.Movie
import com.kidzie.streamview.datasource.remote.RemoteDataSource

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override suspend fun getNowPlayingMovies(count : Int): Result<List<Movie>> {
        return remoteDataSource.requestNowPlayingMovie(count)
    }


    override suspend fun getPopularMovies() {
        return remoteDataSource.requestPopularMovie()
    }

    override suspend fun getTopRatedMovies() {
        return remoteDataSource.requestTopRatedMovie()
    }

    override suspend fun getUpcomingMovies() {
        return remoteDataSource.requestUpcomingMovie()
    }

    override suspend fun getMovieDetail() {
        return remoteDataSource.requestDetailMovie()
    }
}

