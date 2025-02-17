package kldemo.chargestations.presentation.navigator

import androidx.fragment.app.FragmentManager
import kldemo.chargestations.R
import kldemo.chargestations.presentation.cities.CitiesFragment
import kldemo.chargestations.presentation.city_chargers.CityChargersFragment
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor() : AppNavigator {

    private var fragmentManager: FragmentManager? = null

    override fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    override fun navigateTo(screen: Screen) {
        val fragment = when (screen) {
            is Screen.Cities -> CitiesFragment.newInstance()
            is Screen.CityChargers -> CityChargersFragment.newInstance(screen.city)
        }

        fragmentManager?.run {
            beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(fragment::class.java.canonicalName)
                .commit()
        }
    }

    override fun navigateBack() {
        fragmentManager?.run {
            return popBackStack()
        }
    }
}