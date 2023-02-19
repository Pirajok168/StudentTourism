package ru.android.stuttravel.feature.news.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.android.stuttravel.feature.news.presentation.NewsScreen

fun NavGraphBuilder.NewsNavGraph(
    hostNavController: NavHostController,
    padding: PaddingValues,
) {
   composable("newsScreen"){
       NewsScreen(padding = padding)
   }
}