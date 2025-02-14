package kldemo.chargestations.domain.chargestation

import kldemo.chargestations.data.chargestation.ChargeStationRepository
import kldemo.chargestations.data.chargestation.model.ChargeStation
import kldemo.chargestations.data.core.ApiError
import kldemo.chargestations.data.core.ApiResult
import javax.inject.Inject

class ChargeStationManagerImpl @Inject constructor(private val chargeStationRepository: ChargeStationRepository) :
    ChargeStationManager {

    override suspend fun getChargeStations(): ApiResult<List<ChargeStation>, ApiError> {
        return chargeStationRepository.getChargeStations()
    }
}