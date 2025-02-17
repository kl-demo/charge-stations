package kldemo.chargestations.domain.cities

import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult

interface CitiesManager {
    suspend fun getCities(): ApiResult<List<String>, ApiError>
}