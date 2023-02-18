package ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.commons.feature.search.data.database.GetDormitorieById
import ru.shared.IInfoDormitories
import ru.shared.MobileSdk
import ru.shared.feature.seeInfoDormitories.data.IRepoGetInfo
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail


data class StateAboutRoom(
    val room: PresentationRoomsDetail? = null
)

sealed class EventAboutRoom{
    data class LoadData(val id: String): EventAboutRoom()
}

class AboutRoomViewModel(
    private val repo: IRepoGetInfo  = MobileSdk.IInfoDormitories
): ViewModel() {
    var uiState by mutableStateOf(StateAboutRoom())

    fun event(eventAboutRoom: EventAboutRoom){
        when(eventAboutRoom){
            is EventAboutRoom.LoadData ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val data = repo.getRoomById(eventAboutRoom.id)
                    withContext(Dispatchers.Main){
                        uiState = uiState.copy(room = data)
                    }
                }
            }
        }
    }
}