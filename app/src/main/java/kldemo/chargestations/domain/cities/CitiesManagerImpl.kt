package kldemo.chargestations.domain.cities

import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult
import kldemo.chargestations.domain.chargestation.ChargeStationManager
import javax.inject.Inject

class CitiesManagerImpl @Inject constructor(private val chargeStationManager: ChargeStationManager) :
    CitiesManager {
    override suspend fun getCities(): ApiResult<List<String>, ApiError> {
        return when(val apiResult =  chargeStationManager.getChargeStations()){
            is ApiResult.Error -> apiResult
            is ApiResult.Success -> ApiResult.success(apiResult.result.map { it.city }.distinct().sorted())
        }
    }
}