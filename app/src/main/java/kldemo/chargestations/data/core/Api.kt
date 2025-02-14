package kldemo.chargestations.data.core

import com.haroldadmin.cnradapter.NetworkResponse
import kldemo.chargestations.data.chargestation.model.ChargeStation
import retrofit2.http.GET

interface Api {
    @GET("api/charge-stations")
    suspend fun getChargeStations(): NetworkResponse<List<ChargeStation>, ApiError>
}