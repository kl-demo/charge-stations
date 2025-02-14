package kldemo.chargestations.domain.city_chargers

import kldemo.chargestations.data.chargestation.model.Charger
import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult

interface CityChargersManager {
    suspend fun getCityChargers(city: String): ApiResult<List<Charger>, ApiError>
}