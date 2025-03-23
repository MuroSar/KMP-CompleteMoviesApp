package com.murosar.kmp.completemoviesapp.data.mapper

import com.murosar.kmp.completemoviesapp.data.datasource.model.KnownForResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.PersonPagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.PopularPersonResponse
import com.murosar.kmp.completemoviesapp.domain.model.KnownFor
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson

fun PersonPagingResponse.mapToLocalPopularPersonList(): List<PopularPerson> = results.map { it.mapToLocalPopularPerson() }

private fun PopularPersonResponse.mapToLocalPopularPerson() =
    PopularPerson(
        id = id,
        adult = adult,
        gender = gender,
        knownFor = knownFor.map { it.mapToLocalKnownFor() },
        knownForDepartment = knownForDepartment,
        name = name,
        popularity = popularity,
        profilePath = profilePath
    )

private fun KnownForResponse.mapToLocalKnownFor() =
    KnownFor(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        mediaType = mediaType,
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
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
