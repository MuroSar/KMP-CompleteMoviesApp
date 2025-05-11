package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntityComplete
import io.mockative.Mockable

@Mockable
@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(popularMovieEntity: PopularMovieEntity)

    @Transaction
    @Query("SELECT * FROM popular_movie")
    suspend fun getPopularMovies(): List<PopularMovieEntityComplete>
}
