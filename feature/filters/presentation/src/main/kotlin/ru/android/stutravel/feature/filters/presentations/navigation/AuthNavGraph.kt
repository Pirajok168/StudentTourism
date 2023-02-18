package ru.android.stutravel.feature.filters.presentations.navigation

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.android.stutravel.feature.filters.presentations.*


fun NavGraphBuilder.filtersNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = FiltersRoutes.Root.passRoute(),
        startDestination = FiltersRoutes.FiltersScreen.startDest()
    ) {
        composable(
            route = FiltersRoutes.FiltersScreen.passRoute()
        ) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FiltersRoutes.Root.popTo())
            }
            FilterScreen(backHome = {
                navController.popBackStack()
            }, filterSearchViewModel = viewModel(parentEntry), toRegionScreen = {
                navController.navigate(FiltersRoutes.RegionScreen.passRoute())
            }, toCitiesScreen = {
                navController.navigate(FiltersRoutes.CitiesScreen.passRoute())
            }, toFoodScreen =  {
                navController.navigate(FiltersRoutes.FoodScreen.passRoute())
            }, toUniversitiesScreen = {
                navController.navigate(FiltersRoutes.UniversitiesScreen.passRoute())
            }) {
                navController.navigate(FiltersRoutes.TypeRoomScreen.passRoute())
            }

        }

        composable(
            route = FiltersRoutes.RegionScreen.passRoute()
        ){
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FiltersRoutes.Root.popTo())
            }
            RegionScreen(filterSearchViewModel = viewModel(parentEntry)){
                navController.popBackStack()
            }
        }

        composable(
            route = FiltersRoutes.CitiesScreen.passRoute()
        ){
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FiltersRoutes.Root.popTo())
            }
            CitiesScreen(filterSearchViewModel = viewModel(parentEntry)){
                navController.popBackStack()
            }
        }

        composable(
            route = FiltersRoutes.FoodScreen.passRoute()
        ){
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FiltersRoutes.Root.popTo())
            }
            FoodScreen(filterSearchViewModel = viewModel(parentEntry)){
                navController.popBackStack()
            }
        }



        composable(
            route = FiltersRoutes.UniversitiesScreen.passRoute()
        ){
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FiltersRoutes.Root.popTo())
            }
            UniversitiesScreen(filterSearchViewModel = viewModel(parentEntry)){
                navController.popBackStack()
            }
        }

        composable(
            route = FiltersRoutes.TypeRoomScreen.passRoute()
        ){
            val parentEntry = remember(it) {
                navController.getBackStackEntry(FiltersRoutes.Root.popTo())
            }
            TypeRoomScreen(filterSearchViewModel = viewModel(parentEntry)){
                navController.popBackStack()
            }
        }

    }
}