package com.murosar.kmp.completemoviesapp.data.mapper

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import com.murosar.kmp.completemoviesapp.data.database.entity.KnownForEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntityWithKnownForEntity
import com.murosar.kmp.completemoviesapp.data.datasource.model.KnownForResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.PersonPagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.PopularPersonResponse
import com.murosar.kmp.completemoviesapp.domain.model.KnownFor
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import kotlin.test.Test

class PopularPersonMapperTest {

    @Test
    fun `map PersonPagingResponse to PopularPerson list correctly`() {
        val knownForResponse = KnownForResponse(
            id = 1,
            adult = false,
            backdropPath = "backdrop_path",
            firstAirDate = "2021-01-01",
            mediaType = "movie",
            name = "Known For Name",
            originalLanguage = "en",
            originalName = "Original Name",
            originalTitle = "Original Title",
            overview = "Overview",
            popularity = 99.9,
            posterPath = "poster_path",
            releaseDate = "2021-01-01",
            title = "Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1200
        )

        val popularPersonResponse = PopularPersonResponse(
            id = 123,
            adult = false,
            gender = 1,
            knownFor = listOf(knownForResponse),
            knownForDepartment = "Acting",
            name = "Person Name",
            popularity = 100.0,
            profilePath = "profile_path"
        )

        val response = PersonPagingResponse(
            page = 1,
            results = listOf(popularPersonResponse),
            totalPages = 1,
            totalResults = 1
        )

        val result = response.mapToLocalPopularPersonList()

        assertThat(result).hasSize(1)
        val popularPerson = result.first()
        assertThat(popularPerson.id).isEqualTo(popularPersonResponse.id)
        assertThat(popularPerson.adult).isEqualTo(popularPersonResponse.adult)
        assertThat(popularPerson.gender).isEqualTo(popularPersonResponse.gender)
        assertThat(popularPerson.knownForDepartment).isEqualTo(popularPersonResponse.knownForDepartment)
        assertThat(popularPerson.name).isEqualTo(popularPersonResponse.name)
        assertThat(popularPerson.popularity).isEqualTo(popularPersonResponse.popularity)
        assertThat(popularPerson.profilePath).isEqualTo(popularPersonResponse.profilePath)

        assertThat(popularPerson.knownFor).hasSize(1)
        val knownFor = popularPerson.knownFor.first()
        assertThat(knownFor.id).isEqualTo(knownForResponse.id)
        assertThat(knownFor.adult).isEqualTo(knownForResponse.adult)
        assertThat(knownFor.backdropPath).isEqualTo(knownForResponse.backdropPath)
        assertThat(knownFor.firstAirDate).isEqualTo(knownForResponse.firstAirDate)
        assertThat(knownFor.mediaType).isEqualTo(knownForResponse.mediaType)
        assertThat(knownFor.name).isEqualTo(knownForResponse.name)
        assertThat(knownFor.originalLanguage).isEqualTo(knownForResponse.originalLanguage)
        assertThat(knownFor.originalName).isEqualTo(knownForResponse.originalName)
        assertThat(knownFor.originalTitle).isEqualTo(knownForResponse.originalTitle)
        assertThat(knownFor.overview).isEqualTo(knownForResponse.overview)
        assertThat(knownFor.popularity).isEqualTo(knownForResponse.popularity)
        assertThat(knownFor.posterPath).isEqualTo(knownForResponse.posterPath)
        assertThat(knownFor.releaseDate).isEqualTo(knownForResponse.releaseDate)
        assertThat(knownFor.title).isEqualTo(knownForResponse.title)
        assertThat(knownFor.video).isEqualTo(knownForResponse.video)
        assertThat(knownFor.voteAverage).isEqualTo(knownForResponse.voteAverage)
        assertThat(knownFor.voteCount).isEqualTo(knownForResponse.voteCount)
    }

    @Test
    fun `map PopularPerson to PopularPersonEntity correctly`() {
        val popularPerson = PopularPerson(
            id = 123,
            adult = false,
            gender = 1,
            knownFor = emptyList(),
            knownForDepartment = "Acting",
            name = "Person Name",
            popularity = 100.0,
            profilePath = "profile_path"
        )

        val entity = popularPerson.mapToDataBasePopularPerson()

        assertThat(entity.id).isEqualTo(popularPerson.id)
        assertThat(entity.adult).isEqualTo(popularPerson.adult)
        assertThat(entity.gender).isEqualTo(popularPerson.gender)
        assertThat(entity.knownForDepartment).isEqualTo(popularPerson.knownForDepartment)
        assertThat(entity.name).isEqualTo(popularPerson.name)
        assertThat(entity.popularity).isEqualTo(popularPerson.popularity)
        assertThat(entity.profilePath).isEqualTo(popularPerson.profilePath)
    }

    @Test
    fun `map KnownFor to KnownForEntity correctly`() {
        val knownFor = KnownFor(
            id = 1,
            adult = false,
            backdropPath = "backdrop_path",
            firstAirDate = "2021-01-01",
            mediaType = "movie",
            name = "Known For Name",
            originalLanguage = "en",
            originalName = "Original Name",
            originalTitle = "Original Title",
            overview = "Overview",
            popularity = 99.9,
            posterPath = "poster_path",
            releaseDate = "2021-01-01",
            title = "Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1200
        )

        val userId = 123

        val entity = knownFor.mapToDataBaseKnownFor(userId)

        assertThat(entity.id).isEqualTo(knownFor.id)
        assertThat(entity.userId).isEqualTo(userId)
        assertThat(entity.adult).isEqualTo(knownFor.adult)
        assertThat(entity.backdropPath).isEqualTo(knownFor.backdropPath)
        assertThat(entity.firstAirDate).isEqualTo(knownFor.firstAirDate)
        assertThat(entity.mediaType).isEqualTo(knownFor.mediaType)
        assertThat(entity.name).isEqualTo(knownFor.name)
        assertThat(entity.originalLanguage).isEqualTo(knownFor.originalLanguage)
        assertThat(entity.originalName).isEqualTo(knownFor.originalName)
        assertThat(entity.originalTitle).isEqualTo(knownFor.originalTitle)
        assertThat(entity.overview).isEqualTo(knownFor.overview)
        assertThat(entity.popularity).isEqualTo(knownFor.popularity)
        assertThat(entity.posterPath).isEqualTo(knownFor.posterPath)
        assertThat(entity.releaseDate).isEqualTo(knownFor.releaseDate)
        assertThat(entity.title).isEqualTo(knownFor.title)
        assertThat(entity.video).isEqualTo(knownFor.video)
        assertThat(entity.voteAverage).isEqualTo(knownFor.voteAverage)
        assertThat(entity.voteCount).isEqualTo(knownFor.voteCount)
    }

    @Test
    fun `map List of PopularPersonEntityWithKnownForEntity to PopularPerson list correctly`() {
        val knownForEntity = KnownForEntity(
            id = 1,
            userId = 123,
            adult = false,
            backdropPath = "backdrop_path",
            firstAirDate = "2021-01-01",
            mediaType = "movie",
            name = "Known For Name",
            originalLanguage = "en",
            originalName = "Original Name",
            originalTitle = "Original Title",
            overview = "Overview",
            popularity = 99.9,
            posterPath = "poster_path",
            releaseDate = "2021-01-01",
            title = "Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 1200
        )

        val popularPersonEntity = PopularPersonEntity(
            id = 123,
            adult = false,
            gender = 1,
            knownForDepartment = "Acting",
            name = "Person Name",
            popularity = 100.0,
            profilePath = "profile_path"
        )

        val entityWithKnownFor = PopularPersonEntityWithKnownForEntity(
            popularPersonEntity = popularPersonEntity,
            knownForEntity = listOf(knownForEntity)
        )

        val result = listOf(entityWithKnownFor).mapToLocalPopularPersonList()

        assertThat(result).hasSize(1)
        val person = result.first()
        assertThat(person.id).isEqualTo(popularPersonEntity.id)
        assertThat(person.adult).isEqualTo(popularPersonEntity.adult)
        assertThat(person.gender).isEqualTo(popularPersonEntity.gender)
        assertThat(person.knownForDepartment).isEqualTo(popularPersonEntity.knownForDepartment)
        assertThat(person.name).isEqualTo(popularPersonEntity.name)
        assertThat(person.popularity).isEqualTo(popularPersonEntity.popularity)
        assertThat(person.profilePath).isEqualTo(popularPersonEntity.profilePath)

        assertThat(person.knownFor).hasSize(1)
        val knownFor = person.knownFor.first()
        assertThat(knownFor.id).isEqualTo(knownForEntity.id)
        assertThat(knownFor.adult).isEqualTo(knownForEntity.adult)
        assertThat(knownFor.backdropPath).isEqualTo(knownForEntity.backdropPath)
        assertThat(knownFor.firstAirDate).isEqualTo(knownForEntity.firstAirDate)
        assertThat(knownFor.mediaType).isEqualTo(knownForEntity.mediaType)
        assertThat(knownFor.name).isEqualTo(knownForEntity.name)
        assertThat(knownFor.originalLanguage).isEqualTo(knownForEntity.originalLanguage)
        assertThat(knownFor.originalName).isEqualTo(knownForEntity.originalName)
        assertThat(knownFor.originalTitle).isEqualTo(knownForEntity.originalTitle)
        assertThat(knownFor.overview).isEqualTo(knownForEntity.overview)
        assertThat(knownFor.popularity).isEqualTo(knownForEntity.popularity)
        assertThat(knownFor.posterPath).isEqualTo(knownForEntity.posterPath)
        assertThat(knownFor.releaseDate).isEqualTo(knownForEntity.releaseDate)
        assertThat(knownFor.title).isEqualTo(knownForEntity.title)
        assertThat(knownFor.video).isEqualTo(knownForEntity.video)
        assertThat(knownFor.voteAverage).isEqualTo(knownForEntity.voteAverage)
        assertThat(knownFor.voteCount).isEqualTo(knownForEntity.voteCount)
    }
}
