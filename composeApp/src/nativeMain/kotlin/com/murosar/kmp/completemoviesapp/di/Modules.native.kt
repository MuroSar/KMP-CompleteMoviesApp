package com.murosar.kmp.completemoviesapp.di

import androidx.room.Room
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.murosar.kmp.completemoviesapp.data.database.DATABASE_NAME
import com.murosar.kmp.completemoviesapp.data.database.TheMovieDBDB
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual val platformModule = module {
    // HttpClientEngine
    single { Darwin.create() }

    // Room database, TheMovieDBDB
    single {
        Room.databaseBuilder<TheMovieDBDB>(
            name = NSHomeDirectory() + "/$DATABASE_NAME",
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
//            .fallbackToDestructiveMigration(true)
            .build()
    }
}
