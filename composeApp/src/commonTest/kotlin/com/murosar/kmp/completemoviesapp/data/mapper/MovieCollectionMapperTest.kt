package com.murosar.kmp.completemoviesapp.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieCollectionEntity
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieCollectionPagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieCollectionResponse
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import kotlin.test.Test

class MovieCollectionMapperTest {

    @Test
    fun `mapToLocalMovieCollection should map first result to MovieCollection`() {
        val response = MovieCollectionPagingResponse(
            page = 1,
            results = listOf(
                MovieCollectionResponse(
                    id = 1,
                    adult = false,
                    backdropPath = "backdrop.jpg",
                    name = "Collection Name",
                    originalLanguage = "en",
                    originalName = "Original Collection Name",
                    overview = "Some overview",
                    posterPath = "poster.jpg"
                )
            ),
            totalPages = 10,
            totalResults = 3307
        )

        val movieCollection = response.mapToLocalMovieCollection()

        assertThat(movieCollection.id).isEqualTo(1)
        assertThat(movieCollection.adult).isFalse()
        assertThat(movieCollection.backdropPath).isEqualTo("backdrop.jpg")
        assertThat(movieCollection.name).isEqualTo("Collection Name")
        assertThat(movieCollection.originalLanguage).isEqualTo("en")
        assertThat(movieCollection.originalName).isEqualTo("Original Collection Name")
        assertThat(movieCollection.overview).isEqualTo("Some overview")
        assertThat(movieCollection.posterPath).isEqualTo("poster.jpg")
    }

    @Test
    fun `mapToDataBaseMovieCollection should map MovieCollection to MovieCollectionEntity`() {
        val movieCollection = MovieCollection(
            id = 1,
            adult = false,
            backdropPath = "backdrop.jpg",
            name = "Collection Name",
            originalLanguage = "en",
            originalName = "Original Collection Name",
            overview = "Some overview",
            posterPath = "poster.jpg"
        )

        val entity = movieCollection.mapToDataBaseMovieCollection()

        assertThat(entity.id).isEqualTo(1)
        assertThat(entity.adult).isFalse()
        assertThat(entity.backdropPath).isEqualTo("backdrop.jpg")
        assertThat(entity.name).isEqualTo("Collection Name")
        assertThat(entity.originalLanguage).isEqualTo("en")
        assertThat(entity.originalName).isEqualTo("Original Collection Name")
        assertThat(entity.overview).isEqualTo("Some overview")
        assertThat(entity.posterPath).isEqualTo("poster.jpg")
    }

    @Test
    fun `mapToLocalMovieCollection should map MovieCollectionEntity to MovieCollection`() {
        val entity = MovieCollectionEntity(
            id = 1,
            adult = false,
            backdropPath = "backdrop.jpg",
            name = "Collection Name",
            originalLanguage = "en",
            originalName = "Original Collection Name",
            overview = "Some overview",
            posterPath = "poster.jpg"
        )

        val movieCollection = entity.mapToLocalMovieCollection()

        assertThat(movieCollection.id).isEqualTo(1)
        assertThat(movieCollection.adult).isFalse()
        assertThat(movieCollection.backdropPath).isEqualTo("backdrop.jpg")
        assertThat(movieCollection.name).isEqualTo("Collection Name")
        assertThat(movieCollection.originalLanguage).isEqualTo("en")
        assertThat(movieCollection.originalName).isEqualTo("Original Collection Name")
        assertThat(movieCollection.overview).isEqualTo("Some overview")
        assertThat(movieCollection.posterPath).isEqualTo("poster.jpg")
    }

}