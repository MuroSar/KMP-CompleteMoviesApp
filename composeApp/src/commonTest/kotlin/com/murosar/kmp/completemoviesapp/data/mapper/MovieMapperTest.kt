package com.murosar.kmp.completemoviesapp.data.mapper

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.datasource.model.MoviePagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieResponse
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import kotlin.test.Test

class MovieMapperTest {

    @Test
    fun `mapToLocalMovieList should map all MovieResponse fields correctly`() {
        val movieResponse = MovieResponse(
            id = 1,
            adult = false,
            backdropPath = "backdrop.jpg",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Overview of the movie",
            popularity = 99.9,
            posterPath = "poster.jpg",
            releaseDate = "2025-01-01",
            title = "Movie Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1234
        )
        val moviePagingResponse = MoviePagingResponse(
            page = 2689,
            results = listOf(movieResponse),
            totalPages = 8263,
            totalResults = 8538
        )

        val result = moviePagingResponse.mapToLocalMovieList()

        assertThat(result).hasSize(1)
        with(result[0]) {
            assertThat(id).isEqualTo(1)
            assertThat(adult).isFalse()
            assertThat(backdropPath).isEqualTo("backdrop.jpg")
            assertThat(originalLanguage).isEqualTo("en")
            assertThat(originalTitle).isEqualTo("Original Title")
            assertThat(overview).isEqualTo("Overview of the movie")
            assertThat(popularity).isEqualTo(99.9)
            assertThat(posterPath).isEqualTo("poster.jpg")
            assertThat(releaseDate).isEqualTo("2025-01-01")
            assertThat(title).isEqualTo("Movie Title")
            assertThat(video).isFalse()
            assertThat(voteAverage).isEqualTo(8.5)
            assertThat(voteCount).isEqualTo(1234)
        }
    }

    @Test
    fun `mapToDataBasePopularMovie should map all Movie fields correctly`() {
        val result = movie.mapToDataBasePopularMovie()

        assertThat(result.popularMovieId).isEqualTo(movie.id)
        assertThatMovieEntityMatches(movie, result.movieEntity)
    }

    @Test
    fun `mapToDataBaseTopRatedMovie should map all Movie fields correctly`() {
        val result = movie.mapToDataBaseTopRatedMovie()

        assertThat(result.topRatedMovieId).isEqualTo(movie.id)
        assertThatMovieEntityMatches(movie, result.movieEntity)
    }

    @Test
    fun `mapToDataBaseUpcomingMovie should map all Movie fields correctly`() {
        val result = movie.mapToDataBaseUpcomingMovie()

        assertThat(result.upcomingMovieId).isEqualTo(movie.id)
        assertThatMovieEntityMatches(movie, result.movieEntity)
    }

    @Test
    fun `mapToDataBaseRecommendedMovie should map all Movie fields correctly`() {
        val result = movie.mapToDataBaseRecommendedMovie(movieId = 99)

        assertThat(result.recommendedMovieId).isEqualTo(movie.id)
        assertThat(result.originalMovieId).isEqualTo(99)
        assertThatMovieEntityMatches(movie, result.movieEntity)
    }

    @Test
    fun `mapToLocalPopularMovieList should map all fields correctly`() {
        val result = listOf(popularMovieEntityComplete).mapToLocalPopularMovieList()

        assertThat(result).hasSize(1)
        assertThatMovieMatchesEntity(result[0], popularMovieEntityComplete)
    }

    @Test
    fun `mapToLocalTopRatedMovieList should map all fields correctly`() {
        val result = listOf(topRatedMovieEntityComplete).mapToLocalTopRatedMovieList()

        assertThat(result).hasSize(1)
        assertThatMovieMatchesEntity(result[0], topRatedMovieEntityComplete)
    }

    @Test
    fun `mapToLocalUpcomingMovieList should map all fields correctly`() {
        val result = listOf(upcomingMovieEntityComplete).mapToLocalUpcomingMovieList()

        assertThat(result).hasSize(1)
        assertThatMovieMatchesEntity(result[0], upcomingMovieEntityComplete)
    }

    @Test
    fun `mapToLocalRecommendedMovieList should map all fields correctly`() {
        val result = listOf(recommendedMovieEntityComplete).mapToLocalRecommendedMovieList()

        assertThat(result).hasSize(1)
        assertThatMovieMatchesEntity(result[0], recommendedMovieEntityComplete)
    }


    companion object {
        private val movie = Movie(
            id = 1,
            adult = false,
            backdropPath = "backdrop.jpg",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Overview",
            popularity = 99.9,
            posterPath = "poster.jpg",
            releaseDate = "2025-01-01",
            title = "Movie Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1234
        )

        private val movieEntity = MovieEntity(
            id = 1,
            adult = false,
            backdropPath = "backdrop.jpg",
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "Overview",
            popularity = 99.9,
            posterPath = "poster.jpg",
            releaseDate = "2025-01-01",
            title = "Movie Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1234
        )

        private val popularMovieEntityComplete = PopularMovieEntityComplete(
            popularMovieEntity = PopularMovieEntity(
                popularMovieId = 1,
                movieEntity = movieEntity
            ),
            movieEntity = movieEntity
        )

        private val topRatedMovieEntityComplete = TopRatedMovieEntityComplete(
            topRatedMovieEntity = TopRatedMovieEntity(
                topRatedMovieId = 1,
                movieEntity = movieEntity
            ),
            movieEntity = movieEntity
        )

        private val upcomingMovieEntityComplete = UpcomingMovieEntityComplete(
            upcomingMovieEntity = UpcomingMovieEntity(
                upcomingMovieId = 1,
                movieEntity = movieEntity
            ),
            movieEntity = movieEntity
        )

        private val recommendedMovieEntityComplete = RecommendedMovieEntityComplete(
            recommendedMovieEntity = RecommendedMovieEntity(
                recommendedMovieId = 1,
                originalMovieId = 99,
                movieEntity = movieEntity
            ),
            movieEntity = movieEntity
        )

        private fun assertThatMovieEntityMatches(movie: Movie, entity: MovieEntity) {
            assertThat(entity.id).isEqualTo(movie.id)
            assertThat(entity.adult).isEqualTo(movie.adult)
            assertThat(entity.backdropPath).isEqualTo(movie.backdropPath)
            assertThat(entity.originalLanguage).isEqualTo(movie.originalLanguage)
            assertThat(entity.originalTitle).isEqualTo(movie.originalTitle)
            assertThat(entity.overview).isEqualTo(movie.overview)
            assertThat(entity.popularity).isEqualTo(movie.popularity)
            assertThat(entity.posterPath).isEqualTo(movie.posterPath)
            assertThat(entity.releaseDate).isEqualTo(movie.releaseDate)
            assertThat(entity.title).isEqualTo(movie.title)
            assertThat(entity.video).isEqualTo(movie.video)
            assertThat(entity.voteAverage).isEqualTo(movie.voteAverage)
            assertThat(entity.voteCount).isEqualTo(movie.voteCount)
        }

        private fun assertThatMovieMatchesEntity(movie: Movie, entity: PopularMovieEntityComplete) {
            assertThat(movie.id).isEqualTo(entity.popularMovieEntity.popularMovieId)
            assertThatMovieEntityMatches(movie, entity.movieEntity)
        }

        private fun assertThatMovieMatchesEntity(movie: Movie, entity: TopRatedMovieEntityComplete) {
            assertThat(movie.id).isEqualTo(entity.topRatedMovieEntity.topRatedMovieId)
            assertThatMovieEntityMatches(movie, entity.movieEntity)
        }

        private fun assertThatMovieMatchesEntity(movie: Movie, entity: UpcomingMovieEntityComplete) {
            assertThat(movie.id).isEqualTo(entity.upcomingMovieEntity.upcomingMovieId)
            assertThatMovieEntityMatches(movie, entity.movieEntity)
        }

        private fun assertThatMovieMatchesEntity(movie: Movie, entity: RecommendedMovieEntityComplete) {
            assertThat(movie.id).isEqualTo(entity.recommendedMovieEntity.recommendedMovieId)
            assertThatMovieEntityMatches(movie, entity.movieEntity)
        }
    }
}