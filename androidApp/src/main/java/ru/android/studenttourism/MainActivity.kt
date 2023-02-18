package ru.android.studenttourism

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import model.PlatformConfiguration
import ru.android.studenttourism.navigation.StudNavHos
import ru.android.stuttravel.core.theme.StudentTravelTheme

import ru.shared.MobileSdk


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object{
        const val Key = "firstRun"

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref  = getPreferences(Context.MODE_PRIVATE)
        val firstLaunch = sharedPref.getBoolean(Key, true)
        if (firstLaunch){
            with (sharedPref.edit()) {
                putBoolean(Key, false)
                apply()
            }
        }
        //Sync.initialize(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        MobileSdk.init(PlatformConfiguration(this.applicationContext))


        setContent {
            val context = LocalContext.current

            val viewModel: ApplicationViewModel = viewModel()
            StudentTravelTheme {

             val hostNavController = rememberNavController()


                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {


                    StudNavHos(
                        hostNavController = hostNavController,
                        firstLaunch,
                    )

                }

            }
        }
    }
}



