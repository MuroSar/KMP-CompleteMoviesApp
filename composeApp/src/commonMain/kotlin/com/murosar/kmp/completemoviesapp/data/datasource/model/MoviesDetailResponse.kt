package com.murosar.kmp.completemoviesapp.data.datasource.model

import com.murosar.kmp.completemoviesapp.domain.utils.EMPTY_STRING
import com.murosar.kmp.completemoviesapp.domain.utils.ZERO_DOUBLE
import com.murosar.kmp.completemoviesapp.domain.utils.ZERO_INT
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    @SerialName("id") val id: Int,
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("backdrop_path") val backdropPath: String = EMPTY_STRING,
    @SerialName("belongs_to_collection") val belongsToCollection: BelongsToCollectionResponse? = null,
    @SerialName("budget") val budget: Int = ZERO_INT,
    @SerialName("genres") val genres: List<GenreResponse> = emptyList(),
    @SerialName("homepage") val homepage: String = EMPTY_STRING,
    @SerialName("imdb_id") val imdbId: String = EMPTY_STRING,
    @SerialName("origin_country") val originCountry: List<String> = emptyList(),
    @SerialName("original_language") val originalLanguage: String = EMPTY_STRING,
    @SerialName("original_title") val originalTitle: String = EMPTY_STRING,
    @SerialName("overview") val overview: String = EMPTY_STRING,
    @SerialName("popularity") val popularity: Double = ZERO_DOUBLE,
    @SerialName("poster_path") val posterPath: String = EMPTY_STRING,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyResponse> = emptyList(),
    @SerialName("production_countries") val productionCountries: List<ProductionCountryResponse> = emptyList(),
    @SerialName("release_date") val releaseDate: String = EMPTY_STRING,
    @SerialName("revenue") val revenue: Int = ZERO_INT,
    @SerialName("runtime") val runtime: Int = ZERO_INT,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageResponse> = emptyList(),
    @SerialName("status") val status: String = EMPTY_STRING,
    @SerialName("tagline") val tagline: String = EMPTY_STRING,
    @SerialName("title") val title: String = EMPTY_STRING,
    @SerialName("video") val video: Boolean = false,
    @SerialName("vote_average") val voteAverage: Double = ZERO_DOUBLE,
    @SerialName("vote_count") val voteCount: Int = ZERO_INT,
)

@Serializable
data class BelongsToCollectionResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String = EMPTY_STRING,
    @SerialName("poster_path") val posterPath: String = EMPTY_STRING,
    @SerialName("backdrop_path") val backdropPath: String = EMPTY_STRING,
)

@Serializable
data class GenreResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String = EMPTY_STRING,
)

@Serializable
data class ProductionCompanyResponse(
    @SerialName("id") val id: Int,
    @SerialName("logo_path") val logoPath: String = EMPTY_STRING,
    @SerialName("name") val name: String = EMPTY_STRING,
    @SerialName("origin_country") val originCountry: String = EMPTY_STRING,
)

@Serializable
data class ProductionCountryResponse(
    @SerialName("iso_3166_1") val iso_3166_1: String = EMPTY_STRING,
    @SerialName("name") val name: String = EMPTY_STRING,
)

@Serializable
data class SpokenLanguageResponse(
    @SerialName("english_name") val englishName: String = EMPTY_STRING,
    @SerialName("iso_639_1") val iso_639_1: String = EMPTY_STRING,
    @SerialName("name") val name: String = EMPTY_STRING,
)
