package com.kidzie.streamview.datasource.remote

import com.kidzie.streamview.core.Result
import com.kidzie.streamview.datasource.model.Movie

interface RemoteDataSource {
    suspend fun requestNowPlayingMovie(count : Int) : Result<List<Movie>>
    suspend fun requestPopularMovie()
    suspend fun requestTopRatedMovie()
    suspend fun requestUpcomingMovie()
    suspend fun requestDetailMovie()
}