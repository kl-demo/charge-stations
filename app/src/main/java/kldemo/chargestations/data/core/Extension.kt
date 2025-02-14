package kldemo.chargestations.data.core

import com.haroldadmin.cnradapter.NetworkResponse

fun <T> NetworkResponse<T, ApiError>.toApiResult(): ApiResult<T, ApiError> {
    return when (this) {
        is NetworkResponse.Success -> ApiResult.success(body)
        is NetworkResponse.ServerError -> ApiResult.error(this.body ?: ApiError())
        is NetworkResponse.NetworkError -> ApiResult.error(this.body ?: ApiError())
        is NetworkResponse.UnknownError -> ApiResult.error(this.body ?: ApiError())
    }
}