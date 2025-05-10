package com.murosar.kmp.completemoviesapp.data.datasource

import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieCollectionPagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MovieDetailResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.MoviePagingResponse
import com.murosar.kmp.completemoviesapp.data.datasource.model.PersonPagingResponse
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalMovieCollection
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalMovieDetail
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalPopularPersonList
import com.murosar.kmp.completemoviesapp.data.util.ErrorHandler
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TheMovieDBDataSourceImpl(
    private val httpClient: HttpClient,
) : TheMovieDBDataSource {
    override suspend fun getPopularPersonList(): CoroutineResult<List<PopularPerson>> {
        return try {
            val response = httpClient.get(urlString = "person/popular")

            when (response.status.value) {
                in 200..299 -> {
                    val popularPersonListResponse = response.body<PersonPagingResponse>()
                    CoroutineResult.Success(popularPersonListResponse.mapToLocalPopularPersonList())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }

    override suspend fun getPopularMovieList(): CoroutineResult<List<Movie>> {
        return try {
            val response = httpClient.get(urlString = "movie/popular")

            when (response.status.value) {
                in 200..299 -> {
                    val popularMovieListResponse = response.body<MoviePagingResponse>()
                    CoroutineResult.Success(popularMovieListResponse.mapToLocalMovieList())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }

    override suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>> {
        return try {
            val response = httpClient.get(urlString = "movie/top_rated")

            when (response.status.value) {
                in 200..299 -> {
                    val topRatedMovieListResponse = response.body<MoviePagingResponse>()
                    CoroutineResult.Success(topRatedMovieListResponse.mapToLocalMovieList())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }

    override suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>> {
        return try {
            val response = httpClient.get(urlString = "movie/$movieId/recommendations")

            when (response.status.value) {
                in 200..299 -> {
                    val recommendedMovieListResponse = response.body<MoviePagingResponse>()
                    CoroutineResult.Success(recommendedMovieListResponse.mapToLocalMovieList())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }

    override suspend fun getMovieDetailById(movieId: Int): CoroutineResult<MovieDetail> {
        return try {
            val response = httpClient.get(urlString = "movie/$movieId")

            when (response.status.value) {
                in 200..299 -> {
                    val movieResponse = response.body<MovieDetailResponse>()
                    CoroutineResult.Success(movieResponse.mapToLocalMovieDetail())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }

    override suspend fun getMovieCollectionByName(collectionName: String): CoroutineResult<MovieCollection> {
        return try {
            val response = httpClient.get(urlString = "search/collection"){
                url {
                    parameter("query", collectionName)
                }
            }

            when (response.status.value) {
                in 200..299 -> {
                    val collectionResponse = response.body<MovieCollectionPagingResponse>()
                    CoroutineResult.Success(collectionResponse.mapToLocalMovieCollection())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }

    override suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>> {
        return try {
            val response = httpClient.get(urlString = "movie/upcoming")

            when (response.status.value) {
                in 200..299 -> {
                    val upcomingMovieListResponse = response.body<MoviePagingResponse>()
                    CoroutineResult.Success(upcomingMovieListResponse.mapToLocalMovieList())
                }

                else -> ErrorHandler.getError(response)
            }
        } catch (e: Exception) {
            ErrorHandler.handleException(e)
        }
    }
}
