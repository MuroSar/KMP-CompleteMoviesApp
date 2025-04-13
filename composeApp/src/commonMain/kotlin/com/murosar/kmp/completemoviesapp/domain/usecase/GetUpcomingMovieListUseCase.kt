package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
fun interface GetUpcomingMovieListUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetUpcomingMovieListUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetUpcomingMovieListUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> = movieRepository.getUpcomingMovieList()
}
