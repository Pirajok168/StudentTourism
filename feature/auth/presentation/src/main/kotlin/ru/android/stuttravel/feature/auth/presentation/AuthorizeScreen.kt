package ru.android.stuttravel.feature.auth.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.auth.presentation.navigation.AuthRoutes
import ru.android.stuttravel.feature.auth.presentation.viewmodel.AuthViewModel
import ru.android.stuttravel.feature.auth.presentation.viewmodel.Event
import ru.shared.feature.auth.data.model.TypeUser

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AuthorizeScreen(
    toResumeAsNoAuthorized: () -> Unit = {},
    successAuthorized: (typeUser: TypeUser) -> Unit = {},
    toRegistration: () -> Unit = {},
    authViewModel: AuthViewModel = viewModel()
) {
    val uiState = authViewModel.authState
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(uiState){
        Log.e("AuthorizeScreen", uiState.toString())
    }
    LaunchedEffect(uiState.isAuthorized){
        if (uiState.isAuthorized){
            successAuthorized(uiState.typeUser!!)
        }
    }

    LaunchedEffect(uiState.error) {
        if (uiState.error != null) {
            snackbarHostState.showSnackbar(
                message = uiState.error.message ?: "Произошла ошибка при авторизации. Повторите попытку позже",
                duration = SnackbarDuration.Short
            )
            authViewModel.event(Event.DeclineError)
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Авторизация",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.padding(24.dp))
                OutlinedTextField(
                    value = uiState.login,
                    onValueChange = {
                        authViewModel.event(Event.PassLogin(it))
                    },
                    label = {
                        Text(text = "Электронная почта")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(18.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = { authViewModel.event(Event.PassPassword(it)) },
                    label = {
                        Text(text = "Пароль")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(18.dp))
                ElevatedButton(
                    onClick = {
                        keyboard?.hide()
                        authViewModel.event(Event.AuthByLoginPassword)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.loading

                    ) {
                    Text(text = "Войти")
                }

                TextButton(onClick = toRegistration) {
                    Text(text = "Регистрация для путешественников")
                }

                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.outline)
                ) {
                    Text(text = "Забыли пароль?")
                }
            }


        }
    }

}


@Preview
@Composable
private fun Preview() {
    StudentTravelTheme {
        AuthorizeScreen()
    }
}