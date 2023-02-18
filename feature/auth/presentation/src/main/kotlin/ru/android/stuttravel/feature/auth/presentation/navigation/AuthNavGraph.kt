package ru.android.stuttravel.feature.auth.presentation.navigation

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.android.stuttravel.feature.auth.presentation.AuthorizeScreen
import ru.android.stuttravel.feature.auth.presentation.RegistrationScreen
import ru.android.stuttravel.feature.auth.presentation.RestorePasswordScreen
import ru.android.stuttravel.feature.auth.presentation.SplashScreen
import ru.shared.feature.auth.data.model.TypeUser

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    toHomeScreen: (typeUser: TypeUser) -> Unit
) {
    navigation(
        route = AuthRoutes.Root.passRoute(),
        startDestination = AuthRoutes.SplashScreen.startDest()
    ) {
        composable(
            route = AuthRoutes.SplashScreen.passRoute()
        ) {

            SplashScreen(
                onAuthorized = { toHomeScreen(it) },
                notAuthorized = {
                    navController.backQueue.clear()
                    navController.navigate(AuthRoutes.AuthorizeScreen.passRoute())
                },
                authViewModel = viewModel()
            )
        }

        composable(
            route = AuthRoutes.RestorePasswordScreen.passRoute()
        ) {
            RestorePasswordScreen {

            }
        }

        composable(
            route = AuthRoutes.RegistrationScreen.passRoute()
        ) {

            RegistrationScreen(toHomeScreen={
                toHomeScreen(it!!)
            })

        }

        composable(
            route = AuthRoutes.AuthorizeScreen.passRoute()
        ) {

            AuthorizeScreen(
                toResumeAsNoAuthorized = {},
                successAuthorized = toHomeScreen,
                toRegistration = {
                    navController.navigate(AuthRoutes.RegistrationScreen.passRoute())
                },
                authViewModel =  viewModel()
            )
        }
    }
}