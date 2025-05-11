package com.murosar.kmp.completemoviesapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.murosar.kmp.completemoviesapp.data.database.dao.KnownForDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieCollectionDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailBelongsToCollectionDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailGenreDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailProductionCompanyDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailProductionCountryDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailSpokenLanguageDao
import com.murosar.kmp.completemoviesapp.data.database.dao.PopularMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.PopularPersonDao
import com.murosar.kmp.completemoviesapp.data.database.dao.RecommendedMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.TopRatedMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.UpcomingMovieDao
import com.murosar.kmp.completemoviesapp.data.database.entity.KnownForEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailBelongsToCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailGenreEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCompanyEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCountryEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailSpokenLanguageEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntity

const val DATABASE_NAME = "TMDB_DB.db"

@Database(
    entities = [
        PopularPersonEntity::class,
        KnownForEntity::class,
        MovieEntity::class,
        PopularMovieEntity::class,
        TopRatedMovieEntity::class,
        UpcomingMovieEntity::class,
        RecommendedMovieEntity::class,
        MovieDetailEntity::class,
        MovieDetailBelongsToCollectionEntity::class,
        MovieDetailGenreEntity::class,
        MovieDetailProductionCompanyEntity::class,
        MovieDetailProductionCountryEntity::class,
        MovieDetailSpokenLanguageEntity::class,
        MovieCollectionEntity::class,
    ],
    version = 1,
)
@ConstructedBy(MyDatabaseCtor::class)
abstract class TheMovieDBDB : RoomDatabase() {
    abstract fun popularPersonDao(): PopularPersonDao

    abstract fun knownForDao(): KnownForDao

    abstract fun popularMovieDao(): PopularMovieDao

    abstract fun topRatedMovieDao(): TopRatedMovieDao

    abstract fun upcomingMovieDao(): UpcomingMovieDao

    abstract fun recommendedMovieDao(): RecommendedMovieDao

    abstract fun movieDetailDao(): MovieDetailDao

    abstract fun movieDetailBelongsToCollectionDao(): MovieDetailBelongsToCollectionDao

    abstract fun movieDetailGenreDao(): MovieDetailGenreDao

    abstract fun movieDetailProductionCompanyDao(): MovieDetailProductionCompanyDao

    abstract fun movieDetailProductionCountryDao(): MovieDetailProductionCountryDao

    abstract fun movieDetailSpokenLanguageDao(): MovieDetailSpokenLanguageDao

    abstract fun movieCollectionDao(): MovieCollectionDao
}

expect object MyDatabaseCtor : RoomDatabaseConstructor<TheMovieDBDB>
