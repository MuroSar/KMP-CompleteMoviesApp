package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
fun interface GetRecommendedMoviesListByIdUseCase {
    suspend operator fun invoke(movieId: Int): CoroutineResult<List<Movie>>
}

class GetRecommendedMoviesListByIdUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetRecommendedMoviesListByIdUseCase {
    override suspend operator fun invoke(movieId: Int): CoroutineResult<List<Movie>> = movieRepository.getRecommendedMoviesListById(movieId)
}
