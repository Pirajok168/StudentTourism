package ru.android.stuttravel.feature.profile.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.android.stuttravel.feature.profile.presentation.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.profileNavGraph(
    hostNavController: NavHostController,
    padding: PaddingValues,
){
    navigation(
        route = ProfileRoutes.Root.passRoute(),
        startDestination = ProfileRoutes.ProfileScreen.startDest()
    ){
        composable(
            route = ProfileRoutes.ProfileScreen.startDest()
        ){


            ProfileScreen(padding)
        }
    }

}