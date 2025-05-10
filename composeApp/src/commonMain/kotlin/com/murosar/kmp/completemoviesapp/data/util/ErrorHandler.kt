package com.murosar.kmp.completemoviesapp.data.util

import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import io.mockative.Mockable
import kotlinx.serialization.SerializationException

@Mockable(HttpResponse::class)
object ErrorHandler {
    suspend fun getError(response: HttpResponse): CoroutineResult.Failure {
        val statusCode = response.status.value
        val errorBody = runCatching { response.body<String>() }.getOrNull()  // To avoid parsing errors

        return when (statusCode) {
            401 -> CoroutineResult.Failure(
                MovieError.Unauthorized(
                    code = statusCode,
                    message = response.status.description,
                    errorBody = errorBody
                )
            )

            408 -> CoroutineResult.Failure(
                MovieError.RequestTimeout(
                    code = statusCode,
                    message = response.status.description,
                    errorBody = errorBody
                )
            )

            409 -> CoroutineResult.Failure(
                MovieError.Conflict(
                    code = statusCode,
                    message = response.status.description,
                    errorBody = errorBody
                )
            )

            413 -> CoroutineResult.Failure(
                MovieError.PayloadTooLarge(
                    code = statusCode,
                    message = response.status.description,
                    errorBody = errorBody
                )
            )

            in 400..499 -> CoroutineResult.Failure(
                MovieError.ClientError(
                    code = statusCode,
                    message = response.status.description,
                    errorBody = errorBody
                )
            )

            in 500..599 -> CoroutineResult.Failure(
                MovieError.ServerError(
                    code = statusCode,
                    message = response.status.description,
                    errorBody = errorBody
                )
            )

            else -> {
                println("⚠️ Unknown error: $statusCode - ${response.status.description}")
                CoroutineResult.Failure(
                    MovieError.UnknownError(
                        code = statusCode,
                        message = response.status.description,
                        errorBody = errorBody
                    )
                )
            }
        }
    }

    fun handleException(e: Exception): CoroutineResult.Failure {
        return when (e) {
            is UnresolvedAddressException -> CoroutineResult.Failure(MovieError.NoInternet)
            is SerializationException -> CoroutineResult.Failure(MovieError.SerializationError)
            else -> {
                println("⚠️ Unhandled exception: ${e.message}")
                CoroutineResult.Failure(MovieError.UnknownError(code = -1, message = e.message ?: "Unknown error", errorBody = null))
            }
        }
    }
}

