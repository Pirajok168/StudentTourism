package ru.android.stuttravel.feature.auth.presentation.navigation

import androidx.navigation.NavType
import ru.android.stuttravel.core.navigation.Route

sealed class AuthRoutes<T>(route: String? = null, navArgs: Map<T, NavType<*>> = emptyMap()) : Route<T>(route, navArgs) {
    object Root : AuthRoutes<Unit>("auth_root")

    object AuthorizeScreen : AuthRoutes<Unit>()
    object RegistrationScreen : AuthRoutes<Unit>()
    object RestorePasswordScreen : AuthRoutes<Unit>()
    object SplashScreen : AuthRoutes<Unit>()
}