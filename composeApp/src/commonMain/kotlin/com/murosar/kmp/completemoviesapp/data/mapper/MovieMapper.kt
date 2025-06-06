package com.murosar.kmp.completemoviesapp.data.mapper

import com.murosar.kmp.completemoviesapp.data.database.entity.MovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntityComplete
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
        voteCount = voteCount,
    )

fun Movie.mapToDataBasePopularMovie() =
    PopularMovieEntity(
        popularMovieId = id,
        movieEntity =
            MovieEntity(
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
                voteCount = voteCount,
            ),
    )

fun Movie.mapToDataBaseTopRatedMovie() =
    TopRatedMovieEntity(
        topRatedMovieId = id,
        movieEntity =
            MovieEntity(
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
                voteCount = voteCount,
            ),
    )

fun Movie.mapToDataBaseUpcomingMovie() =
    UpcomingMovieEntity(
        upcomingMovieId = id,
        movieEntity =
            MovieEntity(
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
                voteCount = voteCount,
            ),
    )

fun Movie.mapToDataBaseRecommendedMovie(movieId: Int) =
    RecommendedMovieEntity(
        recommendedMovieId = id,
        originalMovieId = movieId,
        movieEntity =
            MovieEntity(
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
                voteCount = voteCount,
            ),
    )

fun List<PopularMovieEntityComplete>.mapToLocalPopularMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }

private fun PopularMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = popularMovieEntity.popularMovieId,
        adult = movieEntity.adult,
        backdropPath = movieEntity.backdropPath,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        releaseDate = movieEntity.releaseDate,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )

fun List<TopRatedMovieEntityComplete>.mapToLocalTopRatedMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }

private fun TopRatedMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = topRatedMovieEntity.topRatedMovieId,
        adult = movieEntity.adult,
        backdropPath = movieEntity.backdropPath,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        releaseDate = movieEntity.releaseDate,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )

fun List<UpcomingMovieEntityComplete>.mapToLocalUpcomingMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }

private fun UpcomingMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = upcomingMovieEntity.upcomingMovieId,
        adult = movieEntity.adult,
        backdropPath = movieEntity.backdropPath,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        releaseDate = movieEntity.releaseDate,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )

fun List<RecommendedMovieEntityComplete>.mapToLocalRecommendedMovieList(): List<Movie> = this.map { it.mapToLocalMovie() }

private fun RecommendedMovieEntityComplete.mapToLocalMovie() =
    Movie(
        id = recommendedMovieEntity.recommendedMovieId,
        adult = movieEntity.adult,
        backdropPath = movieEntity.backdropPath,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        releaseDate = movieEntity.releaseDate,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount,
    )
