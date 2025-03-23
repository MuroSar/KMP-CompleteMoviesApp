package com.murosar.kmp.completemoviesapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform