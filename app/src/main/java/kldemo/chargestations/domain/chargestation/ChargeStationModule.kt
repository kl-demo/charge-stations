package kldemo.chargestations.domain.chargestation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kldemo.chargestations.data.chargestation.ChargeStationRepository

@Module
@InstallIn(ViewModelComponent::class)
object ChargeStationModule {

    @Provides
    @ViewModelScoped
    fun provideChargeStationManager(chargeStationRepository: ChargeStationRepository): ChargeStationManager =
        ChargeStationManagerImpl(chargeStationRepository)
}