package com.murosar.kmp.completemoviesapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieCollection(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val posterPath: String,
)
