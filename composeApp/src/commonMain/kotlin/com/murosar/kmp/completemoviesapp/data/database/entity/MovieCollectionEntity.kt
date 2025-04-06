package com.murosar.kmp.completemoviesapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_collection")
data class MovieCollectionEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val posterPath: String,
)
