package com.murosar.kmp.completemoviesapp.domain.repository

import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
fun interface PersonRepository {
    suspend fun getPopularPersonList(): CoroutineResult<List<PopularPerson>>
}
