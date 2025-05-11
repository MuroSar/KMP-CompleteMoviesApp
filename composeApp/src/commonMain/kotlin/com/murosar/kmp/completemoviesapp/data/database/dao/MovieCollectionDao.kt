package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieCollectionEntity
import io.mockative.Mockable

@Mockable
@Dao
interface MovieCollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCollection(movieCollectionEntity: MovieCollectionEntity)

    @Transaction
    @Query("SELECT * FROM movie_collection WHERE name = :collectionName")
    suspend fun getMovieCollectionByName(collectionName: String): MovieCollectionEntity?
}
