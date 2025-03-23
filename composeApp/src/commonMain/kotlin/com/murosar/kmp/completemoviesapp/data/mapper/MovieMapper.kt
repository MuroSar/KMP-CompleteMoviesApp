package com.murosar.kmp.completemoviesapp.data.mapper

import com.murosar.kmp.completemoviesapp.data.datasource.model.MoviePagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieResponse
import com.murosar.kmp.completemoviesapp.domain.model.Movie

fun MoviePagingResponse.mapToLocalMovieList(): List<Movie> = results.map { it.mapToLocalMovie() }

fun MovieResponse.mapToLocalMovie() =
    Movie(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
