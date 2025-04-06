package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

fun interface GetMovieDetailByIdUseCase {
    suspend operator fun invoke(movieId: Int): CoroutineResult<MovieDetail>
}

class GetMovieDetailByIdUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetMovieDetailByIdUseCase {
    override suspend operator fun invoke(movieId: Int): CoroutineResult<MovieDetail> = movieRepository.getMovieDetailById(movieId)
}
