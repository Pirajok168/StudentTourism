package ru.android.stuttravel.feature.home.presentation.navigation

import androidx.navigation.NavType
import ru.android.stuttravel.core.navigation.Route

sealed class HomeRoutes<T> (route: String? = null, navArgs: Map<T, NavType<*>> = emptyMap()): Route<T>(route, navArgs) {
    object Root: HomeRoutes<Unit>("HomeRoutes")

    object HomeScreen: HomeRoutes<Unit>()
}