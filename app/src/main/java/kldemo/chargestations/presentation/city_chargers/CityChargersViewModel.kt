package kldemo.chargestations.presentation.city_chargers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kldemo.chargestations.data.core.ApiResult
import kldemo.chargestations.domain.city_chargers.CityChargersManager
import kldemo.chargestations.presentation.navigator.AppNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CityChargersViewModel @Inject constructor(
    private val cityChargersManager: CityChargersManager,
    private val appNavigator: AppNavigator
) :
    ViewModel() {

    private val citiesViewStateMutable: MutableLiveData<CityChargersViewState> =
        MutableLiveData<CityChargersViewState>()
    val citiesViewState: LiveData<CityChargersViewState> = citiesViewStateMutable

    init {
        citiesViewStateMutable.value = getInitCityChargersViewState()
    }

    fun setCity(city: String) {
        citiesViewStateMutable.value =
            getCityChargersViewState().copy(
                city = city
            )
        getCityChargers(city)
    }

    fun backToCitiesClicked() {
        appNavigator.navigateBack()
    }

    private fun getCityChargers(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val citiesResult = cityChargersManager.getCityChargers(city)
            withContext(Dispatchers.Main) {
                when (citiesResult) {
                    is ApiResult.Error -> citiesViewStateMutable.value =
                        getCityChargersViewState().copy(
                            isLoading = false
                        )

                    is ApiResult.Success -> citiesViewStateMutable.value =
                        getCityChargersViewState().copy(
                            chargers = citiesResult.result,
                            isLoading = false
                        )
                }

            }
        }
    }

    private fun getCityChargersViewState() =
        citiesViewStateMutable.value ?: getInitCityChargersViewState()

    private fun getInitCityChargersViewState() = CityChargersViewState(
        city = "",
        chargers = emptyList(),
        isLoading = true
    )
}