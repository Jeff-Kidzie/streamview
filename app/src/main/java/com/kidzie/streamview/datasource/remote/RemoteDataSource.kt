package com.kidzie.streamview.datasource.remote

interface RemoteDataSource {
    suspend fun requestNowPlayingMovie()
    suspend fun requestPopularMovie()
    suspend fun requestTopRatedMovie()
    suspend fun requestUpcomingMovie()
    suspend fun requestDetailMovie()
}