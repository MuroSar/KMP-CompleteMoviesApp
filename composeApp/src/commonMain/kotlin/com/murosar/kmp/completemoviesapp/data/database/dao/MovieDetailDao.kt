package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailBelongsToCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailGenreEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCompanyEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCountryEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailSpokenLanguageEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailWithRelations
import io.mockative.Mockable

@Mockable
@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetailEntity: MovieDetailEntity)

    @Transaction
    @Query("SELECT * FROM movie_detail WHERE id = :movieId")
    suspend fun getMovieDetailById(movieId: Int): MovieDetailWithRelations?
}

@Mockable
@Dao
fun interface MovieDetailBelongsToCollectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailBelongsToCollection(movieDetailBelongsToCollectionEntity: MovieDetailBelongsToCollectionEntity)
}

@Mockable
@Dao
fun interface MovieDetailGenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailGenre(movieDetailGenreEntity: MovieDetailGenreEntity)
}

@Mockable
@Dao
fun interface MovieDetailProductionCompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailProductionCompany(movieDetailProductionCompanyEntity: MovieDetailProductionCompanyEntity)
}

@Mockable
@Dao
fun interface MovieDetailProductionCountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailProductionCountry(movieDetailProductionCountryEntity: MovieDetailProductionCountryEntity)
}

@Mockable
@Dao
fun interface MovieDetailSpokenLanguageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailSpokenLanguage(movieDetailSpokenLanguageEntity: MovieDetailSpokenLanguageEntity)
}
