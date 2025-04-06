package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

fun interface GetMovieCollectionByNameUseCase {
    suspend operator fun invoke(collectionName: String): CoroutineResult<MovieCollection>
}

class GetMovieCollectionByNameUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetMovieCollectionByNameUseCase {
    override suspend operator fun invoke(collectionName: String): CoroutineResult<MovieCollection> = movieRepository.getMovieCollectionByName(collectionName)
}
