package ru.android.stuttravel.feature.booking.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.*
import androidx.navigation.compose.composable
import ru.android.stuttravel.core.navigation.Route
import ru.android.stuttravel.feature.booking.presentation.AllBookingScreen
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


sealed class BookingRoutes<T>(route: String? = null, navArgs: Map<T, NavType<*>> = emptyMap()): Route<T>(route, navArgs) {
    object Root: BookingRoutes<Unit>("BookingRoutes")

}
fun NavGraphBuilder.AllBooking(
    inlinePaddingValues: PaddingValues
){
    composable(
        route = BookingRoutes.Root.passRoute(),
    ) {
        AllBookingScreen(inlinePaddingValues)
    }
}