package com.kidzie.streamview.datasource

interface RemoteDataSource {
    suspend fun requestNowPlayingMovie()
    suspend fun requestPopularMovie()
    suspend fun requestTopRatedMovie()
    suspend fun requestUpcomingMovie()
    suspend fun requestDetailMovie()
}