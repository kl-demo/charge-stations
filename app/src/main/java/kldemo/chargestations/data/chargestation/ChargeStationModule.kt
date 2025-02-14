package kldemo.chargestations.data.chargestation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kldemo.chargestations.data.core.Api

@Module
@InstallIn(SingletonComponent::class)
object ChargeStationModule {

    @Provides
    fun provideChargeStationRepository(api: Api): ChargeStationRepository =
        ChargeStationRepositoryImpl(api)
}