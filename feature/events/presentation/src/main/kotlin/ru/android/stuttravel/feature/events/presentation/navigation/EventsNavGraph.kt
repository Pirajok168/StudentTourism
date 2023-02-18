package ru.android.stuttravel.feature.events.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.android.stuttravel.feature.events.presentation.AboutEvent
import ru.android.stuttravel.feature.events.presentation.EventsScreen

fun NavGraphBuilder.EventsNavGraph(
    hostNavController: NavHostController,
) {
    navigation(
        route = "events",
        startDestination = "eventsall",
    ){
        composable(route="eventsall"){
            EventsScreen(toBack = {
                hostNavController.popBackStack()
            }, toCheckInfo = {
                idEvent, idUni ->
                hostNavController.navigate("aboutevent?idEvent=$idEvent&idUni=$idUni")
            })
        }

        composable(
            route="aboutevent?idEvent={idEvent}&idUni={idUni}",
            arguments = listOf(
                navArgument("idEvent"){},
                navArgument("idUni"){}
            )
        ){
            val idEvent = it.arguments?.getString("idEvent")
            val idUni = it.arguments?.getString("idUni")
            AboutEvent(idEvent = idEvent!!, idUni = idUni!!, onBack = {
                hostNavController.popBackStack()
            }, toDetailRoom = {
                hostNavController.navigate("viewingouseDetaiil?id=$it")
            })
        }
    }
}