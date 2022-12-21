package ru.abelyh.movies.model.repository

import retrofit2.Call
import ru.abelyh.movies.Constants
import ru.abelyh.movies.data.MovieDetails
import ru.abelyh.movies.data.Movies
import ru.abelyh.movies.model.apis.ApiInterface

class MoviesDbRepositoryImpl : MoviesDbRepository {

    private  val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY, "ru-RU", 3)
    }

    override fun getMovieDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, Constants.API_KEY, "ru-RU")
    }


}