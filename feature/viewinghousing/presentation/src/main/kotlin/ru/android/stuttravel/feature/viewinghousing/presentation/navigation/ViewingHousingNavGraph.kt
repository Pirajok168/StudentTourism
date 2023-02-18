package ru.android.stuttravel.feature.viewinghousing.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.android.stuttravel.feature.viewinghousing.presentation.AboutRoomScreen
import ru.android.stuttravel.feature.viewinghousing.presentation.ViewingHouses

fun NavGraphBuilder.ViewingHousingNavGraph(
    hostNavController: NavHostController,
) {
    navigation(
        route = "viewingouse",
        startDestination = "ViewingHousingRoutes.ViewingHousingScreen.startDest()"
    ){
        composable(
            route = "viewingouseDetaiil?id={id}",
            arguments = listOf(navArgument("id") {})
        ){
            val id = it.arguments?.getString("id")
            ViewingHouses(onBack = {
               hostNavController.popBackStack()
           },idDormitoies=id!!, toCheckInfoRoom = {
                hostNavController.navigate("viewroom?id=$it")
            }, toCheckEvent = {
                idEvent, idUni ->
                hostNavController.navigate("aboutevent?idEvent=$idEvent&idUni=$idUni")
            }, toBooking = {
                hostNavController.navigate("bookingNavigation?idDormitories=$it")
            })
        }

        composable(
            route = "viewroom?id={id}",
            arguments = listOf(navArgument("id") {})
        ){
            val id = it.arguments?.getString("id")
            AboutRoomScreen(id.orEmpty(), toBack = {
                hostNavController.popBackStack()
            })
        }
    }
}