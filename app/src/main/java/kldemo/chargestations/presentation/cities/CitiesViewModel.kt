package kldemo.chargestations.presentation.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kldemo.chargestations.data.core.ApiResult
import kldemo.chargestations.domain.cities.CitiesManager
import kldemo.chargestations.presentation.navigator.AppNavigator
import kldemo.chargestations.presentation.navigator.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesManager: CitiesManager,
    private val appNavigator: AppNavigator
) :
    ViewModel() {

    private val citiesViewStateMutable: MutableLiveData<CitiesViewState> =
        MutableLiveData<CitiesViewState>()
    val citiesViewState: LiveData<CitiesViewState> = citiesViewStateMutable

    init {
        citiesViewStateMutable.value = getInitCitiesViewState()
        getCities()
    }

    fun showCityChargersClicked() {
        val currentCitiesViewState = getCurrentCitiesViewState()
        if (currentCitiesViewState.selectedPosition >= 0 &&
            currentCitiesViewState.selectedPosition < currentCitiesViewState.cities.count()
        ) {
            appNavigator.navigateTo(Screen.CityChargers(currentCitiesViewState.cities[currentCitiesViewState.selectedPosition]))
        }
    }

    fun citySelected(position: Int) {
        citiesViewStateMutable.value =
            getCurrentCitiesViewState().copy(
                selectedPosition = position
            )
    }

    private fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)//emulate network latency
            val citiesResult = citiesManager.getCities()
            withContext(Dispatchers.Main) {
                when (citiesResult) {
                    is ApiResult.Error -> citiesViewStateMutable.value =
                        getCurrentCitiesViewState().copy(
                            isLoading = false
                        )

                    is ApiResult.Success -> citiesViewStateMutable.value =
                        getCurrentCitiesViewState().copy(
                            cities = citiesResult.result,
                            isLoading = false
                        )
                }

            }
        }
    }

    private fun getCurrentCitiesViewState() =
        citiesViewStateMutable.value ?: getInitCitiesViewState()

    private fun getInitCitiesViewState() = CitiesViewState(
        cities = emptyList(),
        selectedPosition = 0,
        isLoading = true
    )
}