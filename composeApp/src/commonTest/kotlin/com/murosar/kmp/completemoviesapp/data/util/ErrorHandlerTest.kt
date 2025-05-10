package com.murosar.kmp.completemoviesapp.data.util

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import io.mockative.every
import io.mockative.mock
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException

class ErrorHandlerTest {

    private val mockHttpResponse = mock(HttpResponse::class)

    @Test
    fun `getError should return Unauthorized for 401`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode.Unauthorized

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.Unauthorized>()
        val error = result.error as MovieError.Unauthorized
        assertThat(error.code).isEqualTo(401)
    }

    @Test
    fun `getError should return RequestTimeout for 408`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode.RequestTimeout

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.RequestTimeout>()
        val error = result.error as MovieError.RequestTimeout
        assertThat(error.code).isEqualTo(408)
    }

    @Test
    fun `getError should return Conflict for 409`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode.Conflict

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.Conflict>()
        val error = result.error as MovieError.Conflict
        assertThat(error.code).isEqualTo(409)
    }

    @Test
    fun `getError should return PayloadTooLarge for 413`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode.PayloadTooLarge

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.PayloadTooLarge>()
        val error = result.error as MovieError.PayloadTooLarge
        assertThat(error.code).isEqualTo(413)
    }

    @Test
    fun `getError should return ClientError for status 404`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode.NotFound

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.ClientError>()
        val error = result.error as MovieError.ClientError
        assertThat(error.code).isEqualTo(404)
    }

    @Test
    fun `getError should return ServerError for status 500`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode.InternalServerError

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.ServerError>()
        val error = result.error as MovieError.ServerError
        assertThat(error.code).isEqualTo(500)
    }

    @Test
    fun `getError should return UnknownError for unknown status`() = runTest {
        every { mockHttpResponse.status } returns HttpStatusCode(600, "Custom Unknown Error")

        val result = ErrorHandler.getError(mockHttpResponse)

        assertThat(result.error).isInstanceOf<MovieError.UnknownError>()
        val error = result.error as MovieError.UnknownError
        assertThat(error.code).isEqualTo(600)
    }


    @Test
    fun `handleException should return NoInternet for UnresolvedAddressException`() {
        val exception = UnresolvedAddressException()
        val result = ErrorHandler.handleException(exception)

        assertThat(result.error).isEqualTo(MovieError.NoInternet)
    }

    @Test
    fun `handleException should return SerializationError for SerializationException`() {
        val exception = SerializationException("Parsing error")
        val result = ErrorHandler.handleException(exception)

        assertThat(result.error).isEqualTo(MovieError.SerializationError)
    }

    @Test
    fun `handleException should return UnknownError for generic Exception`() {
        val exception = Exception("Something went wrong")
        val result = ErrorHandler.handleException(exception)

        assertThat(result.error).isInstanceOf<MovieError.UnknownError>()
        val unknownError = result.error as MovieError.UnknownError
        assertThat(unknownError.code).isEqualTo(-1)
        assertThat(unknownError.message).isEqualTo("Something went wrong")
    }
}