package ru.android.stuttravel.feature.umbrellanavigation

import androidx.navigation.NavType
import ru.android.stuttravel.core.navigation.Route

sealed class InsideRoutes<T>(route: String? = null, navArgs: Map<T, NavType<*>> = emptyMap()): Route<T>(route, navArgs) {
    object Root: InsideRoutes<Unit>("bottom_bar_root")
    object BottomNavScreen : InsideRoutes<Unit>()
}
