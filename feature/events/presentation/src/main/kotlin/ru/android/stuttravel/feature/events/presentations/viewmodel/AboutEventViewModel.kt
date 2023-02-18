package ru.android.stuttravel.feature.events.presentations.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.shared.IEventRepo
import ru.shared.MobileSdk
import ru.shared.feature.event.data.IRepoEvent
import ru.shared.feature.event.data.model.DetailEvent
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories

data class AboutState(
    val events: DetailEvent? = null,
    val relatedRoom: List<PresentationDetailDormitories> = emptyList()
)

sealed class EventAbout{
    data class LoadData(val idUni: String, val idEvent: String): EventAbout()
}

class AboutEventViewModel(
    private val repoEvent: IRepoEvent = MobileSdk.IEventRepo
): ViewModel() {
    var uiState by mutableStateOf(AboutState())

    fun event(eventAbout: EventAbout){
        when(eventAbout){
            is EventAbout.LoadData -> {
                viewModelScope.launch {
                   val event = repoEvent.getEventAndUniById(eventAbout.idUni, eventAbout.idEvent)

                    val rooms = repoEvent.getRoomsByIdUni(eventAbout.idUni)
                    uiState = uiState.copy(events = event, relatedRoom = rooms)
                }
            }
        }
    }
}
