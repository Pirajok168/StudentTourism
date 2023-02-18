package ru.android.stuttravel.feature.profile.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.shared.IRepoProfile
import ru.shared.MobileSdk
import ru.shared.core.ktor.MobileClient
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.feature.profile.data.IRepoProfile
import ru.shared.feature.profile.data.model.PresentationProfile


data class ProfileState(
    val profile: PresentationProfile? = null,
    val isLoading: Boolean = false,
    val isError: ResponseError? = null
)


sealed class Event{
    object LoadData: Event()
}

class ProfileViewModel(
    private val repo: IRepoProfile = MobileSdk.IRepoProfile
): ViewModel() {
    var uiState by mutableStateOf(ProfileState())
    val mutex = Mutex()
    fun event(event: Event){
        when(event){
            Event.LoadData -> {
                viewModelScope.launch {
                    repo.getProfile()
                        .collectLatest {
                            mutex.withLock(Dispatchers.Main) {
                                uiState = when(it){
                                    is FlowResponse.Error -> uiState.copy(isError = it.error)
                                    is FlowResponse.Loading -> uiState.copy(isLoading = it.isLoading)
                                    is FlowResponse.Success -> uiState.copy(profile = it.data)
                                }
                            }

                        }
                }
            }
        }
    }
}
