package com.murosar.kmp.completemoviesapp.data.mapper

import com.murosar.kmp.completemoviesapp.data.database.entity.MovieCollectionEntity
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieCollectionPagingResponse
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection

fun MovieCollectionPagingResponse.mapToLocalMovieCollection() =
    MovieCollection(
        id = results[0].id,
        adult = results[0].adult,
        backdropPath = results[0].backdropPath,
        name = results[0].name,
        originalLanguage = results[0].originalLanguage,
        originalName = results[0].originalName,
        overview = results[0].overview,
        posterPath = results[0].posterPath,
    )

fun MovieCollection.mapToDataBaseMovieCollection() =
    MovieCollectionEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        posterPath = posterPath,
    )

fun MovieCollectionEntity.mapToLocalMovieCollection() =
    MovieCollection(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        posterPath = posterPath,
    )
