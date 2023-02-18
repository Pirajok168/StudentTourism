package ru.android.stuttravel.feature.umbrellanavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.android.stuttravel.core.navigation.Route
import ru.android.stuttravel.feature.home.presentation.navigation.HomeRoutes

data class NavItem(
    val icon: ImageVector,
    val label: String,
    val badges: Int = 0,
    val route: Route<*>
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScreen(
    modifier: Modifier = Modifier,
    navItem: List<NavItem>,
    contentNavGraph: NavGraphBuilder.(padding: PaddingValues, inlineNavController: NavHostController) -> Unit,
    startDestination: Route<*>,
) {
    val navController: NavHostController = rememberNavController()
    var selectedTab by remember { mutableStateOf(startDestination) }

    Scaffold(

        modifier = Modifier
            .fillMaxSize()

            ,
        contentWindowInsets = WindowInsets.systemBars.only(
            WindowInsetsSides.Horizontal
        ),
        bottomBar = {
            NavigationBar(

            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItem.forEach {screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route.startDest() } == true ,
                        onClick = {
                            navController.navigate(screen.route.passRoute()) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                            selectedTab = screen.route
                        },
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = "")
                        },
                        label = {
                            Text(text = screen.label)
                        },
                    )
                }
            }
        },
    ) { innerPadding ->


        NavHost(navController = navController, startDestination = HomeRoutes.Root.passRoute() ){
            contentNavGraph(innerPadding, navController)
        }
    }
}

