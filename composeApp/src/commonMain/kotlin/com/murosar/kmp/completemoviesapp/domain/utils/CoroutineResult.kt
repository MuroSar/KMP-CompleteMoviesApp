package com.murosar.kmp.completemoviesapp.domain.utils

import com.murosar.kmp.completemoviesapp.domain.model.MovieError

sealed class CoroutineResult<out T : Any> {
    class Success<out T : Any>(
        val data: T,
    ) : CoroutineResult<T>()

    class Failure(
        val error: MovieError,
    ) : CoroutineResult<Nothing>()
}
