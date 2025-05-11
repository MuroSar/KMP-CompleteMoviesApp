package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntityComplete
import io.mockative.Mockable

@Mockable
@Dao
interface RecommendedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendedMovie(recommendedMovieEntity: RecommendedMovieEntity)

    @Transaction
    @Query("SELECT * FROM recommended_movie WHERE originalMovieId = :movieId")
    suspend fun getRecommendedMoviesById(movieId: Int): List<RecommendedMovieEntityComplete>
}
