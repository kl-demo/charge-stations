package kldemo.chargestations.data.chargestation

import kldemo.chargestations.data.chargestation.model.ChargeStation
import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult

interface ChargeStationRepository {
    suspend fun getChargeStations(): ApiResult<List<ChargeStation>, ApiError>
}