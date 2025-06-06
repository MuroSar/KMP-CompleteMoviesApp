package com.murosar.kmp.completemoviesapp.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.murosar.kmp.completemoviesapp.data.database.DATABASE_NAME
import com.murosar.kmp.completemoviesapp.data.database.TheMovieDBDB
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule =
    module {
        // HttpClientEngine
        single { OkHttp.create() }

        // Room database, TheMovieDBDB
        single {
            Room
                .databaseBuilder<TheMovieDBDB>(
                    context = androidContext().applicationContext,
                    name = androidContext().getDatabasePath(DATABASE_NAME).absolutePath,
                ).setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
//            .fallbackToDestructiveMigration(true)
                .build()
        }
    }
