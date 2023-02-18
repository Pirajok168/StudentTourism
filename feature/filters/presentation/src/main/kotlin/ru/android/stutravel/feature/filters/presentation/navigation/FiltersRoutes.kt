package ru.android.stutravel.feature.filters.presentation.navigation

import androidx.navigation.NavType
import ru.android.stuttravel.core.navigation.Route

sealed class FiltersRoutes<T>(route: String? = null, navArgs: Map<T, NavType<*>> = emptyMap()) : Route<T>(route, navArgs) {
    object Root : FiltersRoutes<Unit>("filters_root")
    object FiltersScreen : FiltersRoutes<Unit>()

    object RegionScreen: FiltersRoutes<Unit>()

    object CitiesScreen: FiltersRoutes<Unit>()

    object FoodScreen: FiltersRoutes<Unit>()

    object UniversitiesScreen: FiltersRoutes<Unit>()

    object TypeRoomScreen: FiltersRoutes<Unit>()
}