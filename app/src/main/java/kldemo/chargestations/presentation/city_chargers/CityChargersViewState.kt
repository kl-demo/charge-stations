package kldemo.chargestations.presentation.city_chargers

import kldemo.chargestations.data.chargestation.model.Charger

data class CityChargersViewState(
    val city: String,
    val chargers: List<Charger>,
    val isLoading: Boolean
)
