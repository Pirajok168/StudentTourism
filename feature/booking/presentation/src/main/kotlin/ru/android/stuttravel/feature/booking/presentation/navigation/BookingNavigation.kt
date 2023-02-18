package ru.android.stuttravel.feature.booking.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import ru.android.stuttravel.feature.booking.presentation.BookingScreen

fun NavGraphBuilder.BookingNavigation(
    hostNavController: NavHostController
) {
    composable(
        route = "bookingNavigation?idDormitories={idDormitories}",
        arguments = listOf(navArgument("idDormitories") {})
    ) {
        val id = it.arguments?.getString("idDormitories")
        BookingScreen(onBack = {hostNavController.popBackStack()},idDormitories=id!!)
    }
}