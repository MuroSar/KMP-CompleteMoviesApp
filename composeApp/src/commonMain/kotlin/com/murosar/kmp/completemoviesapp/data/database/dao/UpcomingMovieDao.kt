package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntityComplete
import io.mockative.Mockable

@Mockable
@Dao
interface UpcomingMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovie(upcomingMovieEntity: UpcomingMovieEntity)

    @Transaction
    @Query("SELECT * FROM upcoming_movie")
    suspend fun getUpcomingMovies(): List<UpcomingMovieEntityComplete>
}
