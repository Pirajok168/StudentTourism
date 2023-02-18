package ru.android.studenttourism.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import ru.android.stutravel.feature.filters.presentation.navigation.FiltersRoutes
import ru.android.stutravel.feature.filters.presentation.navigation.filtersNavGraph
import ru.android.stuttravel.feature.auth.presentation.navigation.AuthRoutes
import ru.android.stuttravel.feature.auth.presentation.navigation.authNavGraph
import ru.android.stuttravel.feature.booking.presentation.navigation.BookingNavigation
import ru.android.stuttravel.feature.events.presentation.navigation.EventsNavGraph
import ru.android.stuttravel.feature.umbrellanavigation.InsideNavGraph
import ru.android.stuttravel.feature.umbrellanavigation.InsideRoutes
import ru.android.stuttravel.feature.viewinghousing.presentation.navigation.ViewingHousingNavGraph
import ru.android.sync.work.NotificationWorker
import java.util.concurrent.TimeUnit


@Composable
fun StudNavHos(
    hostNavController: NavHostController,
    firstLaunch: Boolean,
){
    val context = LocalContext.current

    NavHost(navController = hostNavController, startDestination = AuthRoutes.Root.startDest()){
        authNavGraph(hostNavController){
            when(it.userRole){
                "user" ->{
                    WorkManager
                        .getInstance(context)
                        .enqueue(
                            PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                                .setInitialDelay(5, TimeUnit.SECONDS)
                                .build())

                    hostNavController.backQueue.clear()
                    hostNavController.navigate(InsideRoutes.Root.passRoute())
                }
            }
        }

        filtersNavGraph(hostNavController)

        EventsNavGraph(hostNavController)

        InsideNavGraph(navHostController = hostNavController, toViewAboutHouse={
            hostNavController.navigate("viewingouseDetaiil?id=$it")
        }, toFiltersScreen = {
            hostNavController.navigate(FiltersRoutes.Root.passRoute())
        }, toEventsScreen = {
            hostNavController.navigate("events")
        })
        ViewingHousingNavGraph(hostNavController = hostNavController)

        BookingNavigation(hostNavController)
    }
}