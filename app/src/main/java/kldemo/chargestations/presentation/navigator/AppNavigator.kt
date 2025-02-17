package kldemo.chargestations.presentation.navigator

import androidx.fragment.app.FragmentManager

sealed class Screen {
    data object Cities : Screen()
    data class CityChargers(val city: String) : Screen()
}

interface AppNavigator {
    fun setFragmentManager(fragmentManager: FragmentManager)
    fun navigateTo(screen: Screen)
    fun navigateBack()
}