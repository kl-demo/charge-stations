package kldemo.chargestations.domain.city_chargers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kldemo.chargestations.domain.chargestation.ChargeStationManager

@Module
@InstallIn(ViewModelComponent::class)
object CityChargersModule {

    @Provides
    @ViewModelScoped
    fun provideCityChargersManager(chargeStationManager: ChargeStationManager): CityChargersManager =
        CityChargersManagerImpl(chargeStationManager)
}