package ru.android.stuttravel.feature.booking.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shared.IRepoBooking
import ru.shared.IRepoProfile
import ru.shared.MobileSdk
import ru.shared.feature.booking.data.IRepoBooking
import ru.shared.feature.profile.data.IRepoProfile
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories

data class BookingState(
    val dormitories: PresentationDetailDormitories? = null,
    val typeRooms: List<String> = emptyList(),
    val selectedTypeRoom: String = "",
    val fio: String = "",
    val number: String = "",
    val email: String =""
)

sealed class Event{
    data class LoadData(val idDormitories: String, ):Event()

    data class SelectTypeRoom(val selectedTypeRoom: String):Event()
}

class BookingViewModel(
    private val repo: IRepoBooking = MobileSdk.IRepoBooking,
    private val repoUser: IRepoProfile = MobileSdk.IRepoProfile
) : ViewModel() {
    var uiState by mutableStateOf(BookingState())


    fun event(event: Event){
        when(event){
            is Event.LoadData -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val data = repo.getInfoDormitoriesById(event.idDormitories)
                    val profile = repoUser.getProfileIntoBaseData()
                    val typeRooms = data.rooms.map { it.typeRooms }.distinct()

                    withContext(Dispatchers.Main){
                        uiState = uiState.copy(
                            dormitories = data,
                            typeRooms = typeRooms,
                            fio = if (profile == null)  "" else "${profile.middleName } ${profile.firstName} ${profile.lastName}",
                            number = profile?.phone ?: "",
                            email = profile?.username ?: ""
                        )
                    }
                }
            }
            is Event.SelectTypeRoom ->{
                uiState = uiState.copy(selectedTypeRoom = event.selectedTypeRoom)
            }
        }
    }
}