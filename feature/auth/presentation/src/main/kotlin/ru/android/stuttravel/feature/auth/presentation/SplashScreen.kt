package ru.android.stuttravel.feature.auth.presentation

import android.util.Log
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.studenttourism.feature.auth.presentation.R
import ru.android.stuttravel.feature.auth.presentation.viewmodel.AuthViewModel
import ru.android.stuttravel.feature.auth.presentation.viewmodel.Event
import ru.shared.feature.auth.data.model.TypeUser

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun SplashScreen(
    onAuthorized: (typeUser: TypeUser) -> Unit,
    notAuthorized: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    val uiState = authViewModel.authState
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.avd_anim)
    var atEnd by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        authViewModel.event(Event.ToRefreshToken)
        if(!atEnd){
            atEnd = true
        }
    }

    LaunchedEffect(uiState.isAuthorized){
        if (uiState.isAuthorized){
            Log.e("splash","авторизиовались")
            onAuthorized(uiState.typeUser!!)
        }
    }

    LaunchedEffect(uiState.error){
        if (!uiState.isAuthorized && uiState.error != null){
            Log.e("splash","не авторизиовались")
            notAuthorized()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                painter = rememberAnimatedVectorPainter(image, atEnd),
                contentDescription = "",
                modifier = Modifier
                )
        }

    }
}


