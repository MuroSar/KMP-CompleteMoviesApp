package com.murosar.kmp.completemoviesapp.data.datasource.model

import com.murosar.kmp.completemoviesapp.domain.utils.EMPTY_STRING
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCollectionPagingResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieCollectionResponse>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
)

@Serializable
data class MovieCollectionResponse(
    @SerialName("id") val id: Int,
    @SerialName("adult") val adult: Boolean = false,
    @SerialName("backdrop_path") val backdropPath: String = EMPTY_STRING,
    @SerialName("name") val name: String = EMPTY_STRING,
    @SerialName("original_language") val originalLanguage: String = EMPTY_STRING,
    @SerialName("original_name") val originalName: String = EMPTY_STRING,
    @SerialName("overview") val overview: String = EMPTY_STRING,
    @SerialName("poster_path") val posterPath: String = EMPTY_STRING,
)
