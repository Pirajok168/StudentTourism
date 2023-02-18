package ru.android.stuttravel.feature.profile.presentation.navigation

import androidx.navigation.NavType
import ru.android.stuttravel.core.navigation.Route

sealed class ProfileRoutes<T>(route: String? = null, navArgs: Map<T, NavType<*>> = emptyMap()): Route<T>(route, navArgs) {
    object Root: ProfileRoutes<Unit>("profile_root")

    object ProfileScreen: ProfileRoutes<Unit>()
}