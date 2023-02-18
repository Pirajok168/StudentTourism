package ru.android.stuttravel.feature.viewinghousing.presentation.navigation

import androidx.navigation.NavType
import ru.android.stuttravel.core.navigation.Route

enum class Arguments{
    ID
}
sealed class ViewingHousingRoutes(route: String? = null, navArgs: Map<Arguments, NavType<*>> = mapOf(
    Arguments.ID to NavType.StringType
)): Route<Arguments>(route, navArgs) {
    object Root: ViewingHousingRoutes("viewingHousingRoutes_root")

    object  ViewingHousingScreen: ViewingHousingRoutes(
        navArgs = mapOf(
            Arguments.ID to NavType.StringType
        )
    ){
        fun navigate(id: String) = ViewingHousingScreen.navigate{
            when(it){
                Arguments.ID -> id
            }
        }
    }
}