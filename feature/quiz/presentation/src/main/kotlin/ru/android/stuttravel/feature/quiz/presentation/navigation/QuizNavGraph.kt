package ru.android.stuttravel.feature.quiz.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.android.stuttravel.feature.quiz.presentation.QuizScreen

fun NavGraphBuilder.QuizNavGraph(
    hostNavController: NavHostController,
    onEnd: () -> Unit
) {
    navigation(
        route = "quizroute",
        startDestination = "quiz",
    ){
        composable(route="quiz"){
            QuizScreen(){
                onEnd()
            }
        }


    }
}