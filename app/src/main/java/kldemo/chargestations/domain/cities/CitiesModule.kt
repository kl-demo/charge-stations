package kldemo.chargestations.domain.cities

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kldemo.chargestations.domain.chargestation.ChargeStationManager

@Module
@InstallIn(ViewModelComponent::class)
object CitiesModule {

    @Provides
    @ViewModelScoped
    fun provideChargeStationManager(chargeStationManager: ChargeStationManager): CitiesManager =
        CitiesManagerImpl(chargeStationManager)
}