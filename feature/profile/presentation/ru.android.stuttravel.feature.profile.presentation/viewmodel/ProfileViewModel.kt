package ru.android.stuttravel.feature.profile.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.shared.IRepoProfile
import ru.shared.MobileSdk
import ru.shared.core.ktor.MobileClient
import ru.shared.feature.profile.data.IRepoProfile
import ru.shared.feature.profile.data.model.PresentationProfile


data class ProfileState(
    val profile: PresentationProfile? = null
)


sealed class Event{
    object LoadData: Event()
}

class ProfileViewModel(
    private val repo: IRepoProfile = MobileSdk.IRepoProfile
): ViewModel() {
    var uiState by mutableStateOf(ProfileState())

    fun event(event: Event){
        when(event){
            Event.LoadData -> {
                viewModelScope.launch {
                    //val a = repo.getProfile()
                    //Log.e("testw", a.toString())
                }
            }
        }
    }
}
