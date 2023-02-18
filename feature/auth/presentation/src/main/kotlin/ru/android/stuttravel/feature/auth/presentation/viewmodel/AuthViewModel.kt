package ru.android.stuttravel.feature.auth.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shared.IAuthRepository
import ru.shared.MobileSdk
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.feature.auth.data.IAuthRepository
import ru.shared.feature.auth.data.model.TypeUser

data class AuthState(
    val loading: Boolean = false,
    val error: ResponseError? = null,
    val login: String = "",
    val password: String = "",
    val isAuthorized: Boolean = false,
    val typeUser: TypeUser? = null
)



sealed class Event{
    object ToRefreshToken: Event()
    object AuthByLoginPassword: Event()
    object DeclineError: Event()
    data class PassLogin(val login: String): Event()
    data class PassPassword(val password: String): Event()
}

class AuthViewModel(
    private val repository: IAuthRepository = MobileSdk.IAuthRepository
) : ViewModel() {
    var authState by mutableStateOf(AuthState())


    fun event(event: Event){
        when(event){
            is Event.PassLogin -> authState = authState.copy(login = event.login)
            is Event.PassPassword -> authState = authState.copy(password = event.password)
            Event.ToRefreshToken -> {
                authState = authState.copy(loading = true)
                refreshToken()
            }
            Event.AuthByLoginPassword -> {
                authState = authState.copy(loading = true)
                auth()
            }
            Event.DeclineError -> authState = authState.copy(error = null)
        }
    }

    private fun auth(){
        viewModelScope.launch(Dispatchers.IO) {

            authState = when(val response = repository.auth(authState.login, authState.password)){
                is ResponseRequest.Error -> withContext(Dispatchers.Main) {authState.copy(error = response.error, loading = false)}
                is ResponseRequest.Success -> {
                    val typeRole = repository.typeUser()

                    withContext(Dispatchers.Main) {
                        authState.copy(
                            isAuthorized = true,
                            loading = false,
                            typeUser = typeRole
                        )
                    }
                }
            }

        }

    }

    private fun refreshToken(){


        viewModelScope.launch(Dispatchers.IO) {
            authState = when (val response = repository.refreshToken()){
                is ResponseRequest.Error -> withContext(Dispatchers.Main){authState.copy(error = response.error)}
                is ResponseRequest.Success -> {
                    val typeRole = repository.typeUser()
                    withContext(Dispatchers.Main) { authState.copy(isAuthorized = true,   typeUser = typeRole) }
                }
            }
            authState = authState.copy(loading = false)
        }

    }

}