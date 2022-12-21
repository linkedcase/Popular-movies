package ru.abelyh.movies.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.abelyh.movies.data.MovieDetails
import ru.abelyh.movies.data.Movies
import ru.abelyh.movies.model.repository.MoviesDbRepository
import ru.abelyh.movies.model.repository.MoviesDbRepositoryImpl

class MoviesViewModel {

    private val _movies = MutableLiveData<List<ru.abelyh.movies.data.Result?>>()
    val movies: LiveData<List<ru.abelyh.movies.data.Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val mMoviesRepository: MoviesDbRepository = MoviesDbRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                Log.d("testLogs", "onResponse Success: ${call.toString()}")
                _movies.postValue(response?.body()?.results)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("testLogs", "onFailure: ${t?.message}")
            }
        })
    }

    fun getMovieDetails(id: Int) {
        val response = mMoviesRepository.getMovieDetails(id)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                Log.d("testLogs", "onResponse Success: ${call.toString()}")
                _movieDetails.postValue(response?.body())
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                Log.d("testLogs", "onFailure: ${t?.message}")
            }
        })
    }
}