package ru.android.stuttravel.feature.umbrellanavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.android.stuttravel.feature.booking.presentation.navigation.AllBooking
import ru.android.stuttravel.feature.booking.presentation.navigation.BookingRoutes
import ru.android.stuttravel.feature.home.presentation.navigation.HomeRoutes
import ru.android.stuttravel.feature.home.presentation.navigation.homeNavGraph
import ru.android.stuttravel.feature.news.presentation.navigation.NewsNavGraph
import ru.android.stuttravel.feature.profile.presentation.navigation.ProfileRoutes
import ru.android.stuttravel.feature.profile.presentation.navigation.profileNavGraph


fun NavGraphBuilder.InsideNavGraph(
    navHostController: NavHostController,
    toViewAboutHouse: (id: String) -> Unit = {},
    toFiltersScreen: () -> Unit,
    toEventsScreen: () -> Unit,
    toViewAboutEvent:(idEvent:String, idUni: String) ->Unit
) {
    navigation(
        route = InsideRoutes.Root.passRoute(),
        startDestination = InsideRoutes.BottomNavScreen.startDest(),
    ) {
        composable(
            route = InsideRoutes.BottomNavScreen.passRoute(),
        ) {
            BottomNavScreen(
                modifier = Modifier,
                navItem = listOf(
                    NavItem(
                        Icons.Outlined.Home,
                        label = "Главная",
                        route = HomeRoutes.Root
                    ),
                    NavItem(
                        icon = Icons.Outlined.Schedule,
                        label = "Заявки",
                        route = BookingRoutes.Root
                    ),
                    NavItem(
                        icon = Icons.Outlined.Person,
                        label = "Профиль",
                        route = ProfileRoutes.Root
                    ),



                ),
                contentNavGraph = { padding, inlineNavController ->

                    homeNavGraph(
                        inlineNavController,
                        padding,
                        toViewAboutHouse,
                        toFiltersScreen,
                        toEventsScreen,
                        toNewsScreen = {
                            inlineNavController.navigate("newsScreen")
                        }, toViewAboutEvent=toViewAboutEvent)
                    profileNavGraph(inlineNavController, padding)

                    AllBooking(padding)

                    NewsNavGraph(inlineNavController, padding)
                },
                startDestination = HomeRoutes.Root
            )
        }

    }

}