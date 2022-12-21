package ru.abelyh.movies.model.repository

import retrofit2.Call
import ru.abelyh.movies.data.MovieDetails
import ru.abelyh.movies.data.Movies

interface MoviesDbRepository {

    /**
     * Returns list of popular [Movies]
     */
    fun getMovies(): Call<Movies>

    /**
     * Returns information for a single movie by returning [MovieDetails]
     */
    fun getMovieDetails(id: Int): Call<MovieDetails>
}