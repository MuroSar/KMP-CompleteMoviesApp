package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
fun interface GetPopularMovieListUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetPopularMovieListUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetPopularMovieListUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> = movieRepository.getPopularMovieList()
}
