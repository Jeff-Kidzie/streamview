package com.kidzie.streamview.datasource.remote

class RemoteDataSourceImpl(
    private val movieService: MovieService
) : RemoteDataSource {

    override suspend fun requestNowPlayingMovie() {
        // TODO: Implement now playing movie request
    }

    override suspend fun requestPopularMovie() {
        // TODO: Implement popular movie request
    }

    override suspend fun requestTopRatedMovie() {
        // TODO: Implement top rated movie request
    }

    override suspend fun requestUpcomingMovie() {
        // TODO: Implement upcoming movie request
    }

    override suspend fun requestDetailMovie() {
        // TODO: Implement detail movie request
    }
}

