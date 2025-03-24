package com.murosar.kmp.completemoviesapp.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: String,
    val budget: Int,
    val homepage: String,
    val imdbId: String,
    val originCountry: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)

@Entity(tableName = "movie_detail_genre")
data class MovieDetailGenreEntity(
    @PrimaryKey val id: Int,
    val movieId: Int,
    val name: String,
)

@Entity(tableName = "movie_detail_production_company")
data class MovieDetailProductionCompanyEntity(
    @PrimaryKey val id: Int,
    val movieId: Int,
    val name: String,
    val logoPath: String?,
    val originCountry: String,
)

@Entity(tableName = "movie_detail_production_country")
data class MovieDetailProductionCountryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val isoCode: String,
    val name: String,
)

@Entity(tableName = "movie_detail_spoken_language")
data class MovieDetailSpokenLanguageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val isoCode: String,
    val name: String,
    val englishName: String,
)

data class MovieDetailWithRelations(
    @Embedded val movieDetail: MovieDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val genres: List<MovieDetailGenreEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val productionCompanies: List<MovieDetailProductionCompanyEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val productionCountries: List<MovieDetailProductionCountryEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val spokenLanguages: List<MovieDetailSpokenLanguageEntity>,
)

