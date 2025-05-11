package com.murosar.kmp.completemoviesapp.di

import com.murosar.kmp.completemoviesapp.data.database.TheMovieDBDB
import com.murosar.kmp.completemoviesapp.data.database.TheMovieDBDatabaseImpl
import com.murosar.kmp.completemoviesapp.data.datasource.TheMovieDBDataSourceImpl
import com.murosar.kmp.completemoviesapp.data.repository.MovieRepositoryImpl
import com.murosar.kmp.completemoviesapp.data.repository.PersonRepositoryImpl
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.repository.PersonRepository
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieCollectionByNameUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieCollectionByNameUseCaseImpl
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieDetailByIdUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieDetailByIdUseCaseImpl
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularMovieListUseCaseImpl
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularPersonListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularPersonListUseCaseImpl
import com.murosar.kmp.completemoviesapp.domain.usecase.GetRecommendedMoviesListByIdUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetRecommendedMoviesListByIdUseCaseImpl
import com.murosar.kmp.completemoviesapp.domain.usecase.GetTopRatedMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetTopRatedMovieListUseCaseImpl
import com.murosar.kmp.completemoviesapp.domain.usecase.GetUpcomingMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetUpcomingMovieListUseCaseImpl
import com.murosar.kmp.completemoviesapp.ui.screens.moviedetail.MovieDetailViewModel
import com.murosar.kmp.completemoviesapp.ui.screens.movielist.MovieListViewModel
import com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist.PopularPersonListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule =
    module {
        factory { Dispatchers.IO }
        viewModelOf(::PopularPersonListViewModel)
        viewModelOf(::MovieDetailViewModel)
        viewModelOf(::MovieListViewModel)
    }

val useCaseModule =
    module {
        singleOf(::GetPopularMovieListUseCaseImpl).bind<GetPopularMovieListUseCase>()
        singleOf(::GetTopRatedMovieListUseCaseImpl).bind<GetTopRatedMovieListUseCase>()
        singleOf(::GetRecommendedMoviesListByIdUseCaseImpl).bind<GetRecommendedMoviesListByIdUseCase>()
        singleOf(::GetPopularPersonListUseCaseImpl).bind<GetPopularPersonListUseCase>()
        singleOf(::GetMovieDetailByIdUseCaseImpl).bind<GetMovieDetailByIdUseCase>()
        singleOf(::GetMovieCollectionByNameUseCaseImpl).bind<GetMovieCollectionByNameUseCase>()
        singleOf(::GetUpcomingMovieListUseCaseImpl).bind<GetUpcomingMovieListUseCase>()
    }

val repositoryModule =
    module {
        singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
        singleOf(::PersonRepositoryImpl).bind<PersonRepository>()
    }

val datasourceModule =
    module {
        singleOf(::TheMovieDBDataSourceImpl).bind<TheMovieDBDataSource>()
    }

val apiModule =
    module {
        single {
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                coerceInputValues = true // Force missing or null values to use the data class's default value
                isLenient = true // Allows more flexibility when parsing JSON
            }
        }
        single {
            HttpClient(get<HttpClientEngine>()) {
                // Default config for each request
                install(DefaultRequest) {
                    url("https://api.themoviedb.org/3/")
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    header(HttpHeaders.Accept, ContentType.Application.Json)
                    header(
                        HttpHeaders.Authorization,
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NGIyMzg3NDdkNzU2YjM2YzU0MjQ1YjQ5YWUxNWFmZCIsInN1YiI6IjVkZDFlMjdhZmQ2ZmExMDAxMjgwMDQzNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._pCVHHGwuR2hDlguJ8Gph6lITx65cQNPMmzJOO6LksA",
                    )
                }
                // Serialization with Kotlinx
                install(ContentNegotiation) {
                    json(
                        json = get<Json>(),
                        contentType = ContentType.Any,
                    )
                }
                // Logging
                install(Logging) {
                    logger =
                        object : Logger {
                            override fun log(message: String) {
                                println("Ktor Log: $message")
                            }
                        }
                    level = LogLevel.ALL
                }
                // Error management
                install(HttpCallValidator) {
                    handleResponseExceptionWithRequest { exception, request ->
                        println("Request to ${request.url} failed: ${exception.message}")
                    }
                }
                // Timeouts
                install(HttpTimeout) {
                    requestTimeoutMillis = 15_000
                    connectTimeoutMillis = 10_000
                    socketTimeoutMillis = 15_000
                }
                // Error management
                expectSuccess = true // Throw an exception if there were HTTP error (400+)
            }
        }
    }

val databaseModule =
    module {
        singleOf(::TheMovieDBDatabaseImpl).bind<TheMovieDBDatabase>()

        single { get<TheMovieDBDB>().popularPersonDao() }
        single { get<TheMovieDBDB>().knownForDao() }
        single { get<TheMovieDBDB>().popularMovieDao() }
        single { get<TheMovieDBDB>().topRatedMovieDao() }
        single { get<TheMovieDBDB>().upcomingMovieDao() }
        single { get<TheMovieDBDB>().recommendedMovieDao() }
        single { get<TheMovieDBDB>().movieDetailDao() }
        single { get<TheMovieDBDB>().movieDetailBelongsToCollectionDao() }
        single { get<TheMovieDBDB>().movieDetailGenreDao() }
        single { get<TheMovieDBDB>().movieDetailProductionCompanyDao() }
        single { get<TheMovieDBDB>().movieDetailProductionCountryDao() }
        single { get<TheMovieDBDB>().movieDetailSpokenLanguageDao() }
        single { get<TheMovieDBDB>().movieCollectionDao() }
    }
