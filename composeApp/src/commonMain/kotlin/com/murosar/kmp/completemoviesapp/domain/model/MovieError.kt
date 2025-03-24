package com.murosar.kmp.completemoviesapp.domain.model

sealed class MovieError(
    open val code: Int = ERROR_CODE_UNKNOWN,
    open val message: String = ERROR_MESSAGE_UNKNOWN,
    open val errorBody: String? = null,
) {
    data class RequestTimeout(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data class Unauthorized(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data class Conflict(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data object NoInternet : MovieError()
    data class PayloadTooLarge(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data class ClientError(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data class ServerError(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data object SerializationError : MovieError()
    data class UnknownError(
        override val code: Int,
        override val message: String,
        override val errorBody: String?,
    ) : MovieError(
        code = code,
        message = message,
        errorBody = errorBody
    )

    data object DataBaseError : MovieError()

    companion object {
        private const val ERROR_CODE_UNKNOWN = -1
        private const val ERROR_MESSAGE_UNKNOWN = "UNKNOWN ERROR"
    }
}
