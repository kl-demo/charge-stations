package kldemo.chargestations.presentation.cities

data class CitiesViewState(
    val cities: List<String>,
    val selectedPosition: Int,
    val isLoading: Boolean
)
