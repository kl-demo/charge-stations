package kldemo.chargestations.domain.city_chargers

import kldemo.chargestations.data.chargestation.model.Charger
import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult
import kldemo.chargestations.domain.chargestation.ChargeStationManager
import javax.inject.Inject

class CityChargersManagerImpl @Inject constructor(private val chargeStationManager: ChargeStationManager) :
    CityChargersManager {

    override suspend fun getCityChargers(city: String): ApiResult<List<Charger>, ApiError> {
        return when (val apiResult = chargeStationManager.getChargeStations()) {
            is ApiResult.Error -> apiResult
            is ApiResult.Success -> ApiResult.success(apiResult.result.filter { it.city == city }
                .map { it.charger }.sortedBy { it.name })
        }
    }
}