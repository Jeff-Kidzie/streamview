package com.kidzie.streamview.datasource.remote

import com.kidzie.streamview.datasource.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("now_playing?language=en-US")
    suspend fun fetchNowPlayingMovies(@Query("limit") limit : Int,@Query("page") page: Int = 1): Response<MovieResponse>
}

data class MovieResponse(
    val results: List<Movie>
)
