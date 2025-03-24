package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntityComplete

@Dao
interface TopRatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovie(topRatedMovieEntity: TopRatedMovieEntity)

    @Transaction
    @Query("SELECT * FROM top_rated_movie")
    suspend fun getTopRatedMovies(): List<TopRatedMovieEntityComplete>
}
