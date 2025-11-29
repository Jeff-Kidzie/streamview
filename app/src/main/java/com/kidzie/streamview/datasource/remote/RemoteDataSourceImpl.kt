package com.kidzie.streamview.datasource.remote

  import com.kidzie.streamview.core.Result
import com.kidzie.streamview.datasource.model.Movie
import retrofit2.HttpException
import java.io.IOException

class RemoteDataSourceImpl(
    private val movieService: MovieService
) : RemoteDataSource {

    override suspend fun requestNowPlayingMovie(count : Int): Result<List<Movie>> {
        return try {
            val response = movieService.fetchNowPlayingMovies(1)
            if (response.isSuccessful) {
                val data = response.body()?.results ?: emptyList()
                Result.Success(data)
            } else {
                Result.Error(
                    HttpException(response),
                    "HTTP ${response.code()}: ${response.message()}"
                )
            }
        } catch (e: IOException) {
            Result.Error(e, "Network error: ${e.message}")
        } catch (e: Exception) {
            Result.Error(e, "Unknown error: ${e.message}")
        }
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

