package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

fun interface GetMovieDetailUseCase {
    suspend operator fun invoke(movieId: Int): CoroutineResult<Movie>
}

class GetMovieDetailUseCaseImpl(
    private val movieRepository: MovieRepository,
) : GetMovieDetailUseCase {
    override suspend operator fun invoke(movieId: Int): CoroutineResult<Movie> = movieRepository.getMovieDetail(movieId)
}
