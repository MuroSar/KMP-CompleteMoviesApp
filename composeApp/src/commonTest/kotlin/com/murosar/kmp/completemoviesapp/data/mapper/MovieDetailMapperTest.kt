package com.murosar.kmp.completemoviesapp.data.mapper

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isTrue
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailBelongsToCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailGenreEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCompanyEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCountryEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailSpokenLanguageEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailWithRelations
import com.murosar.kmp.completemoviesapp.data.datasource.model.BelongsToCollectionResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.GenreResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieDetailResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.ProductionCompanyResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.ProductionCountryResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.SpokenLanguageResponse
import com.murosar.kmp.completemoviesapp.domain.model.BelongsToCollection
import com.murosar.kmp.completemoviesapp.domain.model.Genre
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.ProductionCompany
import com.murosar.kmp.completemoviesapp.domain.model.ProductionCountry
import com.murosar.kmp.completemoviesapp.domain.model.SpokenLanguage
import kotlin.test.Test

class MovieDetailMapperTest {
    @Test
    fun `mapToLocalMovieDetail should map all fields correctly from MovieDetailResponse`() {
        val response =
            MovieDetailResponse(
                adult = false,
                backdropPath = "backdrop_path.jpg",
                belongsToCollection =
                    BelongsToCollectionResponse(
                        id = 10,
                        name = "Collection Name",
                        posterPath = "collection_poster.jpg",
                        backdropPath = "collection_backdrop.jpg",
                    ),
                budget = 150000000,
                genres = listOf(GenreResponse(1, "Action")),
                homepage = "https://movie.homepage",
                id = 123,
                imdbId = "tt987654",
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalTitle = "Original Title",
                overview = "An awesome movie",
                popularity = 10.5,
                posterPath = "poster.jpg",
                productionCompanies =
                    listOf(
                        ProductionCompanyResponse(1, "logo_path.png", "Company Name", "US"),
                    ),
                productionCountries =
                    listOf(
                        ProductionCountryResponse("US", "United States"),
                    ),
                releaseDate = "2025-12-25",
                revenue = 500000000,
                runtime = 120,
                spokenLanguages =
                    listOf(
                        SpokenLanguageResponse("English", "en", "English"),
                    ),
                status = "Released",
                tagline = "Epic Adventure",
                title = "Movie Title",
                video = true,
                voteAverage = 8.7,
                voteCount = 4500,
            )

        val result = response.mapToLocalMovieDetail()

        assertThat(result.adult).isEqualTo(false)
        assertThat(result.backdropPath).isEqualTo("backdrop_path.jpg")
        assertThat(result.belongsToCollection).isNotNull()
        assertThat(result.belongsToCollection!!.id).isEqualTo(10)
        assertThat(result.belongsToCollection!!.name).isEqualTo("Collection Name")
        assertThat(result.belongsToCollection!!.posterPath).isEqualTo("collection_poster.jpg")
        assertThat(result.belongsToCollection!!.backdropPath).isEqualTo("collection_backdrop.jpg")
        assertThat(result.budget).isEqualTo(150000000)
        assertThat(result.genres).hasSize(1)
        assertThat(result.genres.first().id).isEqualTo(1)
        assertThat(result.genres.first().name).isEqualTo("Action")
        assertThat(result.homepage).isEqualTo("https://movie.homepage")
        assertThat(result.id).isEqualTo(123)
        assertThat(result.imdbId).isEqualTo("tt987654")
        assertThat(result.originCountry).isEqualTo("[US]")
        assertThat(result.originalLanguage).isEqualTo("en")
        assertThat(result.originalTitle).isEqualTo("Original Title")
        assertThat(result.overview).isEqualTo("An awesome movie")
        assertThat(result.popularity).isEqualTo(10.5)
        assertThat(result.posterPath).isEqualTo("poster.jpg")
        assertThat(result.productionCompanies).hasSize(1)
        assertThat(result.productionCompanies.first().id).isEqualTo(1)
        assertThat(result.productionCompanies.first().logoPath).isEqualTo("logo_path.png")
        assertThat(result.productionCompanies.first().name).isEqualTo("Company Name")
        assertThat(result.productionCompanies.first().originCountry).isEqualTo("US")
        assertThat(result.productionCountries).hasSize(1)
        assertThat(result.productionCountries.first().iso_3166_1).isEqualTo("US")
        assertThat(result.productionCountries.first().name).isEqualTo("United States")
        assertThat(result.releaseDate).isEqualTo("2025-12-25")
        assertThat(result.revenue).isEqualTo(500000000)
        assertThat(result.runtime).isEqualTo(120)
        assertThat(result.spokenLanguages).hasSize(1)
        assertThat(result.spokenLanguages.first().englishName).isEqualTo("English")
        assertThat(result.spokenLanguages.first().iso_639_1).isEqualTo("en")
        assertThat(result.spokenLanguages.first().name).isEqualTo("English")
        assertThat(result.status).isEqualTo("Released")
        assertThat(result.tagline).isEqualTo("Epic Adventure")
        assertThat(result.title).isEqualTo("Movie Title")
        assertThat(result.video).isEqualTo(true)
        assertThat(result.voteAverage).isEqualTo(8.7)
        assertThat(result.voteCount).isEqualTo(4500)
    }

    @Test
    fun `mapToDataBaseMovieDetail should map all fields correctly`() {
        val movieDetail =
            MovieDetail(
                adult = true,
                backdropPath = "backdrop.jpg",
                belongsToCollection = null,
                budget = 200000000,
                genres = emptyList(),
                homepage = "https://homepage.com",
                id = 456,
                imdbId = "tt555666",
                originCountry = "US",
                originalLanguage = "en",
                originalTitle = "Original Movie Title",
                overview = "Movie overview",
                popularity = 7.2,
                posterPath = "poster_path.jpg",
                productionCompanies = emptyList(),
                productionCountries = emptyList(),
                releaseDate = "2024-10-10",
                revenue = 800000000,
                runtime = 130,
                spokenLanguages = emptyList(),
                status = "In Production",
                tagline = "The Next Big Thing",
                title = "Upcoming Movie",
                video = false,
                voteAverage = 9.1,
                voteCount = 1200,
            )

        val entity = movieDetail.mapToDataBaseMovieDetail()

        assertThat(entity.adult).isTrue()
        assertThat(entity.backdropPath).isEqualTo("backdrop.jpg")
        assertThat(entity.budget).isEqualTo(200000000)
        assertThat(entity.homepage).isEqualTo("https://homepage.com")
        assertThat(entity.id).isEqualTo(456)
        assertThat(entity.imdbId).isEqualTo("tt555666")
        assertThat(entity.originCountry).isEqualTo("US")
        assertThat(entity.originalLanguage).isEqualTo("en")
        assertThat(entity.originalTitle).isEqualTo("Original Movie Title")
        assertThat(entity.overview).isEqualTo("Movie overview")
        assertThat(entity.popularity).isEqualTo(7.2)
        assertThat(entity.posterPath).isEqualTo("poster_path.jpg")
        assertThat(entity.releaseDate).isEqualTo("2024-10-10")
        assertThat(entity.revenue).isEqualTo(800000000)
        assertThat(entity.runtime).isEqualTo(130)
        assertThat(entity.status).isEqualTo("In Production")
        assertThat(entity.tagline).isEqualTo("The Next Big Thing")
        assertThat(entity.title).isEqualTo("Upcoming Movie")
        assertThat(entity.video).isFalse()
        assertThat(entity.voteAverage).isEqualTo(9.1)
        assertThat(entity.voteCount).isEqualTo(1200)
    }

    @Test
    fun `mapToDataBaseMovieDetailBelongsToCollection should map all fields correctly`() {
        val belongsToCollection =
            BelongsToCollection(
                id = 5,
                name = "Marvel Collection",
                posterPath = "poster_path.jpg",
                backdropPath = "backdrop_path.jpg",
            )

        val result = belongsToCollection.mapToDataBaseMovieDetailBelongsToCollection(movieDetailId = 100)

        assertThat(result.id).isEqualTo(5)
        assertThat(result.movieId).isEqualTo(100)
        assertThat(result.name).isEqualTo("Marvel Collection")
        assertThat(result.posterPath).isEqualTo("poster_path.jpg")
        assertThat(result.backdropPath).isEqualTo("backdrop_path.jpg")
    }

    @Test
    fun `mapToDataBaseMovieDetailGenre should map all fields correctly`() {
        val genre =
            Genre(
                id = 7,
                name = "Adventure",
            )

        val result = genre.mapToDataBaseMovieDetailGenre(movieDetailId = 101)

        assertThat(result.id).isEqualTo(7)
        assertThat(result.movieId).isEqualTo(101)
        assertThat(result.name).isEqualTo("Adventure")
    }

    @Test
    fun `mapToDataBaseMovieDetailProductionCompany should map all fields correctly`() {
        val productionCompany =
            ProductionCompany(
                id = 9,
                logoPath = "company_logo.png",
                name = "Pixar Animation Studios",
                originCountry = "US",
            )

        val result = productionCompany.mapToDataBaseMovieDetailProductionCompany(movieDetailId = 102)

        assertThat(result.id).isEqualTo(9)
        assertThat(result.movieId).isEqualTo(102)
        assertThat(result.logoPath).isEqualTo("company_logo.png")
        assertThat(result.name).isEqualTo("Pixar Animation Studios")
        assertThat(result.originCountry).isEqualTo("US")
    }

    @Test
    fun `mapToDataBaseMovieDetailProductionCountry should map all fields correctly`() {
        val productionCountry =
            ProductionCountry(
                iso_3166_1 = "JP",
                name = "Japan",
            )

        val result = productionCountry.mapToDataBaseMovieDetailProductionCountry(movieDetailId = 103)

        assertThat(result.movieId).isEqualTo(103)
        assertThat(result.isoCode).isEqualTo("JP")
        assertThat(result.name).isEqualTo("Japan")
    }

    @Test
    fun `mapToDataBaseMovieDetailSpokenLanguage should map all fields correctly`() {
        val spokenLanguage =
            SpokenLanguage(
                iso_639_1 = "fr",
                name = "Français",
                englishName = "French",
            )

        val result = spokenLanguage.mapToDataBaseMovieDetailSpokenLanguage(movieDetailId = 104)

        assertThat(result.movieId).isEqualTo(104)
        assertThat(result.isoCode).isEqualTo("fr")
        assertThat(result.name).isEqualTo("Français")
        assertThat(result.englishName).isEqualTo("French")
    }

    @Test
    fun `mapToLocalMovieDetail should map all fields correctly from MovieDetailWithRelations`() {
        val withRelations =
            MovieDetailWithRelations(
                movieDetail =
                    MovieDetailEntity(
                        id = 789,
                        adult = false,
                        backdropPath = "relation_backdrop.jpg",
                        budget = 50000000,
                        homepage = "https://relation.homepage",
                        imdbId = "tt112233",
                        originCountry = "GB",
                        originalLanguage = "en",
                        originalTitle = "Related Original Title",
                        overview = "Relation overview",
                        popularity = 6.4,
                        posterPath = "relation_poster.jpg",
                        releaseDate = "2026-05-20",
                        revenue = 90000000,
                        runtime = 110,
                        status = "Released",
                        tagline = "Relation Tagline",
                        title = "Related Movie",
                        video = false,
                        voteAverage = 7.5,
                        voteCount = 300,
                    ),
                belongsToCollection =
                    MovieDetailBelongsToCollectionEntity(
                        id = 2,
                        movieId = 789,
                        name = "Related Collection",
                        posterPath = "collection_poster_path.jpg",
                        backdropPath = "collection_backdrop_path.jpg",
                    ),
                genres = listOf(MovieDetailGenreEntity(id = 2, movieId = 789, name = "Drama")),
                productionCompanies =
                    listOf(
                        MovieDetailProductionCompanyEntity(
                            id = 2,
                            movieId = 789,
                            logoPath = "logo_relation.png",
                            name = "Relation Company",
                            originCountry = "GB",
                        ),
                    ),
                productionCountries = listOf(MovieDetailProductionCountryEntity(movieId = 789, isoCode = "GB", name = "United Kingdom")),
                spokenLanguages =
                    listOf(
                        MovieDetailSpokenLanguageEntity(
                            movieId = 789,
                            isoCode = "en",
                            name = "English",
                            englishName = "English",
                        ),
                    ),
            )

        val movieDetail = withRelations.mapToLocalMovieDetail()

        assertThat(movieDetail.id).isEqualTo(789)
        assertThat(movieDetail.adult).isFalse()
        assertThat(movieDetail.backdropPath).isEqualTo("relation_backdrop.jpg")
        assertThat(movieDetail.belongsToCollection).isNotNull()
        assertThat(movieDetail.belongsToCollection!!.id).isEqualTo(2)
        assertThat(movieDetail.belongsToCollection!!.name).isEqualTo("Related Collection")
        assertThat(movieDetail.belongsToCollection!!.posterPath).isEqualTo("collection_poster_path.jpg")
        assertThat(movieDetail.belongsToCollection!!.backdropPath).isEqualTo("collection_backdrop_path.jpg")
        assertThat(movieDetail.budget).isEqualTo(50000000)
        assertThat(movieDetail.genres).hasSize(1)
        assertThat(movieDetail.genres.first().id).isEqualTo(2)
        assertThat(movieDetail.genres.first().name).isEqualTo("Drama")
        assertThat(movieDetail.homepage).isEqualTo("https://relation.homepage")
        assertThat(movieDetail.imdbId).isEqualTo("tt112233")
        assertThat(movieDetail.originCountry).isEqualTo("GB")
        assertThat(movieDetail.originalLanguage).isEqualTo("en")
        assertThat(movieDetail.originalTitle).isEqualTo("Related Original Title")
        assertThat(movieDetail.overview).isEqualTo("Relation overview")
        assertThat(movieDetail.popularity).isEqualTo(6.4)
        assertThat(movieDetail.posterPath).isEqualTo("relation_poster.jpg")
        assertThat(movieDetail.productionCompanies).hasSize(1)
        assertThat(movieDetail.productionCompanies.first().id).isEqualTo(2)
        assertThat(movieDetail.productionCompanies.first().logoPath).isEqualTo("logo_relation.png")
        assertThat(movieDetail.productionCompanies.first().name).isEqualTo("Relation Company")
        assertThat(movieDetail.productionCompanies.first().originCountry).isEqualTo("GB")
        assertThat(movieDetail.productionCountries).hasSize(1)
        assertThat(movieDetail.productionCountries.first().iso_3166_1).isEqualTo("GB")
        assertThat(movieDetail.productionCountries.first().name).isEqualTo("United Kingdom")
        assertThat(movieDetail.releaseDate).isEqualTo("2026-05-20")
        assertThat(movieDetail.revenue).isEqualTo(90000000)
        assertThat(movieDetail.runtime).isEqualTo(110)
        assertThat(movieDetail.spokenLanguages).hasSize(1)
        assertThat(movieDetail.spokenLanguages.first().englishName).isEqualTo("English")
        assertThat(movieDetail.spokenLanguages.first().iso_639_1).isEqualTo("en")
        assertThat(movieDetail.spokenLanguages.first().name).isEqualTo("English")
        assertThat(movieDetail.status).isEqualTo("Released")
        assertThat(movieDetail.tagline).isEqualTo("Relation Tagline")
        assertThat(movieDetail.title).isEqualTo("Related Movie")
        assertThat(movieDetail.video).isFalse()
        assertThat(movieDetail.voteAverage).isEqualTo(7.5)
        assertThat(movieDetail.voteCount).isEqualTo(300)
    }
}
