package kldemo.chargestations.data.core

sealed class ApiResult<out S, out E> {
    data class Success<S>(val result: S) : ApiResult<S, Nothing>()
    data class Error<E>(val error: E) : ApiResult<Nothing, E>()

    companion object {
        fun <S> success(value: S): Success<S> = Success(value)
        fun <E> error(error: E): Error<E> = Error(error)
    }
}