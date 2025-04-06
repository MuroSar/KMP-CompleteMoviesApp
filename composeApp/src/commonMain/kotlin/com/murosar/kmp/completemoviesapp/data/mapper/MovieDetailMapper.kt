package com.murosar.kmp.completemoviesapp.data.mapper

import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailBelongsToCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailGenreEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCompanyEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCountryEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailSpokenLanguageEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailWithRelations
import com.murosar.kmp.completemoviesapp.data.datasource.model.BelongsToCollectionResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.GenreResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieDetailResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.ProductionCompanyResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.ProductionCountryResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.SpokenLanguageResponse
import com.murosar.kmp.completemoviesapp.domain.model.BelongsToCollection
import com.murosar.kmp.completemoviesapp.domain.model.Genre
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.ProductionCompany
import com.murosar.kmp.completemoviesapp.domain.model.ProductionCountry
import com.murosar.kmp.completemoviesapp.domain.model.SpokenLanguage
import com.murosar.kmp.completemoviesapp.domain.utils.EMPTY_STRING

fun MovieDetailResponse.mapToLocalMovieDetail() =
    MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollection?.mapToLocalBelongsToCollection(),
        budget = budget,
        genres = genres.mapToLocalGenreList(),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originCountry = originCountry.toString(),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies.mapToLocalProductionCompaniesList(),
        productionCountries = productionCountries.mapToLocalProductionCountriesList(),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.mapToLocalSpokenLanguagesList(),
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

private fun BelongsToCollectionResponse.mapToLocalBelongsToCollection() =
    BelongsToCollection(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
    )

private fun List<GenreResponse>.mapToLocalGenreList(): List<Genre> = map { Genre(id = it.id, name = it.name) }

private fun List<ProductionCompanyResponse>.mapToLocalProductionCompaniesList(): List<ProductionCompany> = map {
    ProductionCompany(
        id = it.id,
        logoPath = it.logoPath,
        name = it.name,
        originCountry = it.originCountry
    )
}

private fun List<ProductionCountryResponse>.mapToLocalProductionCountriesList(): List<ProductionCountry> =
    map { ProductionCountry(iso_3166_1 = it.iso_3166_1, name = it.name) }

private fun List<SpokenLanguageResponse>.mapToLocalSpokenLanguagesList(): List<SpokenLanguage> = map {
    SpokenLanguage(
        englishName = it.englishName,
        iso_639_1 = it.iso_639_1,
        name = it.name
    )
}

fun MovieDetail.mapToDataBaseMovieDetail() =
    MovieDetailEntity(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

fun BelongsToCollection.mapToDataBaseMovieDetailBelongsToCollection(movieDetailId: Int): MovieDetailBelongsToCollectionEntity =
    MovieDetailBelongsToCollectionEntity(
        id = id,
        movieId = movieDetailId,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
    )

fun Genre.mapToDataBaseMovieDetailGenre(movieDetailId: Int): MovieDetailGenreEntity =
    MovieDetailGenreEntity(
        id = id,
        movieId = movieDetailId,
        name = name
    )

fun ProductionCompany.mapToDataBaseMovieDetailProductionCompany(movieDetailId: Int): MovieDetailProductionCompanyEntity =
    MovieDetailProductionCompanyEntity(
        id = id,
        movieId = movieDetailId,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )


fun ProductionCountry.mapToDataBaseMovieDetailProductionCountry(movieDetailId: Int): MovieDetailProductionCountryEntity =
    MovieDetailProductionCountryEntity(
        movieId = movieDetailId,
        isoCode = iso_3166_1,
        name = name
    )

fun SpokenLanguage.mapToDataBaseMovieDetailSpokenLanguage(movieDetailId: Int): MovieDetailSpokenLanguageEntity =
    MovieDetailSpokenLanguageEntity(
        movieId = movieDetailId,
        isoCode = iso_639_1,
        name = name,
        englishName = englishName
    )

fun MovieDetailWithRelations.mapToLocalMovieDetail() =
    MovieDetail(
        id = movieDetail.id,
        adult = movieDetail.adult,
        backdropPath = movieDetail.backdropPath,
        belongsToCollection = BelongsToCollection(
            id = belongsToCollection.id,
            name = belongsToCollection.name,
            posterPath = belongsToCollection.posterPath,
            backdropPath = belongsToCollection.backdropPath,
        ),
        budget = movieDetail.budget,
        genres = genres.map { Genre(id = it.id, name = it.name) },
        homepage = movieDetail.homepage,
        imdbId = movieDetail.imdbId,
        originCountry = movieDetail.originCountry,
        originalLanguage = movieDetail.originalLanguage,
        originalTitle = movieDetail.originalTitle,
        overview = movieDetail.overview,
        popularity = movieDetail.popularity,
        posterPath = movieDetail.posterPath,
        productionCompanies = productionCompanies.map {
            ProductionCompany(
                id = it.id,
                logoPath = it.logoPath ?: EMPTY_STRING,
                name = it.name,
                originCountry = it.originCountry
            )
        },
        productionCountries = productionCountries.map { ProductionCountry(iso_3166_1 = it.isoCode, name = it.name) },
        releaseDate = movieDetail.releaseDate,
        revenue = movieDetail.revenue,
        runtime = movieDetail.runtime,
        spokenLanguages = spokenLanguages.map {
            SpokenLanguage(
                englishName = it.englishName,
                iso_639_1 = it.isoCode,
                name = it.name
            )
        },
        status = movieDetail.status,
        tagline = movieDetail.tagline,
        title = movieDetail.title,
        video = movieDetail.video,
        voteAverage = movieDetail.voteAverage,
        voteCount = movieDetail.voteCount,
    )
