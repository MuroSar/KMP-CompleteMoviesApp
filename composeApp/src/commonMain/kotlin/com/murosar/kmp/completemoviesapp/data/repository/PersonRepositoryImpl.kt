package com.murosar.kmp.completemoviesapp.data.repository

import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.repository.PersonRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

class PersonRepositoryImpl(
    private val theMovieDBDataSource: TheMovieDBDataSource,
    private val theMovieDBDatabase: TheMovieDBDatabase,
) : PersonRepository {
    override suspend fun getPopularPersonList(): CoroutineResult<List<PopularPerson>> {
        return when (val serviceResult = theMovieDBDataSource.getPopularPersonList()) {
            is CoroutineResult.Success -> {
                println("✅ getPopularPersonList SUCCESS, inserting into database")
                theMovieDBDatabase.insertPopularPersons(serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getPopularPersonList FAILS, trying to get from database")
                theMovieDBDatabase.getPopularPersons()
            }
        }
    }
}
