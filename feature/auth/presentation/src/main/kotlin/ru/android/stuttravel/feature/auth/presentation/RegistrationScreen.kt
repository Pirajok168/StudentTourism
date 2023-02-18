package ru.android.stuttravel.feature.auth.presentation

import android.util.Log
import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.auth.presentation.viewmodel.AuthViewModel
import ru.shared.feature.auth.data.model.TypeUser


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen(
    toHomeScreen: (typeUser: TypeUser?) -> Unit,
) {

    var fio by remember {
        mutableStateOf("")
    }


    var phoneNumber by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val options = listOf("Студент", "Молодой специалист")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }


    val focus = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal)
            )
            .statusBarsPadding()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Для путешественников",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.outline
            ),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .offset(y = (-15).dp)
                .padding(horizontal = 16.dp)
        )

        OutlinedTextField(
            value = fio,
            onValueChange = { fio = it },
            label = {
                Text(text = "ФИО")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            maxLines = 1,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                },

            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, capitalization = KeyboardCapitalization.Words)
        )
        Spacer(modifier = Modifier.padding(18.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .onFocusChanged {
                    if (it.hasFocus) {
                        expanded = true
                    }
                }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text("Роль") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = Color.Transparent),

                )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            focus.moveFocus(FocusDirection.Down)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }


        Spacer(modifier = Modifier.padding(18.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = {
                Text(text = "Номер телефона")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                },
            )
        )
        Spacer(modifier = Modifier.padding(18.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {

                email = it
            },
            label = {
                Text(text = "Электронная почта")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,  imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                    focus.moveFocus(FocusDirection.Down)
                },
            )
        )
        Spacer(modifier = Modifier.padding(18.dp))

        var passwordHidden by rememberSaveable { mutableStateOf(true) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "Пароль")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .onFocusChanged {

                    if (it.hasFocus) {
                        coroutineScope.launch {
                            delay(500)

                            scrollState.animateScrollTo(scrollState.maxValue)

                        }
                    }
                }

                ,
            maxLines = 1,
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone  = {
                    keyboardController?.hide()
                },
            ),
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            }
        )
        Spacer(modifier = Modifier.padding(18.dp))

        ElevatedButton(
            onClick = { toHomeScreen(null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
                .imePadding(),

            ) {
            Text(text = "Зарегистрироваться")
        }
        TextButton(onClick = {}, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.outline)) {
            Text(text = "Нажимая, вы соглашаетесь с\n" +
                    "правилами обработки и передачи данных", textAlign = TextAlign.Center, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.padding(16.dp))

    }

}
class EmailValidator {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email);
        }
    }
}
fun isEmail(str: String): Boolean{
    return str.isNotBlank() && EmailValidator.isEmailValid(str)
}

@Preview()
@Composable
private fun Preview() {
    StudentTravelTheme {
        RegistrationScreen(){}
    }
}