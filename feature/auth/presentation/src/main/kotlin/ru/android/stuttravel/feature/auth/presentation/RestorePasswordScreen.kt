package ru.android.stuttravel.feature.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RestorePasswordScreen(
    successAuthorized: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Тут ввод логина пароля")

        Button(onClick = successAuthorized) {
            Text(text = "Продолжить как авторизованный пользователь")
        }
    }
}