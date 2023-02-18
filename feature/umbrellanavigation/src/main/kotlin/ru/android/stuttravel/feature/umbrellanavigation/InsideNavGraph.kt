package ru.android.stuttravel.feature.umbrellanavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.android.stuttravel.feature.home.presentation.navigation.HomeRoutes
import ru.android.stuttravel.feature.home.presentation.navigation.homeNavGraph


fun NavGraphBuilder.InsideNavGraph(
    navHostController: NavHostController,
    toViewAboutHouse: (id: String) -> Unit = {},
    toFiltersScreen: () -> Unit,
    toEventsScreen: () ->Unit,
){
    navigation(
        route = InsideRoutes.Root.passRoute(),
        startDestination = InsideRoutes.BottomNavScreen.startDest(),
    ){
        composable(
           route = InsideRoutes.BottomNavScreen.passRoute(),
        ){
            BottomNavScreen(
                modifier = Modifier
                    ,
                navItem = listOf(
                    NavItem(
                        Icons.Outlined.Home,
                        label = "Главная",
                        route = HomeRoutes.Root
                    ),

                ),
                contentNavGraph = {  padding, inlineNavController ->

                    homeNavGraph(inlineNavController, padding, toViewAboutHouse, toFiltersScreen, toEventsScreen, toNewsScreen = {

                    })


                },
                startDestination = HomeRoutes.Root
            )
        }

    }

}