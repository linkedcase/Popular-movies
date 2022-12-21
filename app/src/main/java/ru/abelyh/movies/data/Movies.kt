package ru.abelyh.movies.data

import ru.abelyh.movies.data.Result

data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)