package kldemo.chargestations.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kldemo.chargestations.R
import kldemo.chargestations.presentation.navigator.AppNavigator
import kldemo.chargestations.presentation.navigator.Screen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appNavigator.setFragmentManager(supportFragmentManager)
        if (savedInstanceState == null) {
            appNavigator.navigateTo(Screen.Cities)
        }
    }
}