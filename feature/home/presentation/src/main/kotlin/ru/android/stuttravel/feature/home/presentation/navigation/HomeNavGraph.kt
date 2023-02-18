package ru.android.stuttravel.feature.home.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.android.stuttravel.feature.home.presentation.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    hostNavController: NavHostController,
    padding: PaddingValues,
    toViewAboutHouse: (id: String) -> Unit = {},
    toFiltersScreen: () -> Unit,
    toEventsScreen: () ->Unit,
    toNewsScreen: () ->Unit,
    toViewAboutEvent:(idEvent:String, idUni: String) ->Unit
) {
    navigation(
        route = HomeRoutes.Root.passRoute(),
        startDestination = HomeRoutes.HomeScreen.startDest()
    ) {
        composable(
            route = HomeRoutes.HomeScreen.startDest()
        ) {
            val parentEntry = remember(it) {
                hostNavController.getBackStackEntry(HomeRoutes.Root.popTo())
            }

            HomeScreen(
                padding,
                toViewAboutHouse = toViewAboutHouse,
                toFiltersScreen = toFiltersScreen,
                homeViewModel = viewModel(parentEntry),
                toEventsScreen = toEventsScreen,
                toNewsScreen = toNewsScreen,
                toViewAboutEvent = toViewAboutEvent
            )
        }
    }
}