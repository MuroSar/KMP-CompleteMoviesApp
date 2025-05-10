package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
fun interface GetTopRatedMovieListUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetTopRatedMovieListUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetTopRatedMovieListUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<Movie>> = movieRepository.getTopRatedMovieList()
}
