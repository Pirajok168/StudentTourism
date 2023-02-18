package ru.android.studenttourism.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.android.stuttravel.feature.auth.presentation.navigation.AuthRoutes
import ru.android.stuttravel.feature.auth.presentation.navigation.authNavGraph
import ru.android.stuttravel.feature.umbrellanavigation.InsideNavGraph
import ru.android.stuttravel.feature.umbrellanavigation.InsideRoutes
import ru.android.stuttravel.feature.viewinghousing.presentation.navigation.ViewingHousingNavGraph


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
                    hostNavController.backQueue.clear()
                    hostNavController.navigate(InsideRoutes.Root.passRoute())
                }
            }
        }



        InsideNavGraph(navHostController = hostNavController, toViewAboutHouse={
            hostNavController.navigate("viewingouseDetaiil?id=$it")
        }, toFiltersScreen = {

        }, toEventsScreen = {

        })
        ViewingHousingNavGraph(hostNavController = hostNavController)


    }
}