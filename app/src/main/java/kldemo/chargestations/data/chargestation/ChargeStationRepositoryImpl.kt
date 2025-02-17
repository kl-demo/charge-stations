package kldemo.chargestations.data.chargestation

import kldemo.chargestations.data.chargestation.model.ChargeStation
import kldemo.chargestations.data.core.Api
import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult
import kldemo.chargestations.data.core.toApiResult
import javax.inject.Inject

class ChargeStationRepositoryImpl @Inject constructor(private val api: Api) :
    ChargeStationRepository {

    override suspend fun getChargeStations(): ApiResult<List<ChargeStation>, ApiError> =
        api.getChargeStations().toApiResult()
}