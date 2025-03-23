package com.murosar.kmp.completemoviesapp

import android.app.Application
import com.murosar.kmp.completemoviesapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApp)
        }
    }
}
