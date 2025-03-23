package com.murosar.kmp.completemoviesapp.data.repository

import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.repository.PersonRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

class PersonRepositoryImpl(
    private val theMovieDBDataSource: TheMovieDBDataSource,
) : PersonRepository {
    override suspend fun getPopularPersonList(): CoroutineResult<List<PopularPerson>> {
        return theMovieDBDataSource.getPopularPersonList()
    }
}
