package ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.RoomService
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import ru.shared.IEventRepo
import ru.shared.IInfoDormitories
import ru.shared.MobileSdk
import ru.shared.feature.event.data.IRepoEvent
import ru.shared.feature.event.data.model.RelatingEventDormitories
import ru.shared.feature.seeInfoDormitories.data.IRepoGetInfo
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail


sealed class TypeOpportunity(
    val label: String,
    val icon: ImageVector,
    val index: Int
) {
    object Rooms : TypeOpportunity(
        label = "Комнаты",
        icon = Icons.Outlined.RoomService,
        index = 0
    )

    object Services : TypeOpportunity(
        label = "Услуги",
        icon = Icons.Outlined.Info,
        index = 1
    )

    object Conditions : TypeOpportunity(
        label = "Условия заселения",
        icon = Icons.Outlined.Rule,
        index = 2
    )

    object EventNear : TypeOpportunity(
        label = "События рядом",
        icon = Icons.Outlined.EventAvailable,
        index = 3
    )

}

data class State(
    val detail: PresentationDetailDormitories? = null,
    val event: List<RelatingEventDormitories> = emptyList(),
    val tabs: List<TypeOpportunity> = listOf(TypeOpportunity.Rooms, TypeOpportunity.Services, TypeOpportunity.Conditions),
    val selectedTab: TypeOpportunity = TypeOpportunity.Rooms
)

sealed class Event {
    data class LoadInfo(val id: String) : Event()
    data class SelecteTab(val typeOpportunity: TypeOpportunity): Event()
}

class ViewModelInfo(
    private val repo: IRepoGetInfo = MobileSdk.IInfoDormitories,
    private val repoEvent: IRepoEvent = MobileSdk.IEventRepo
) : ViewModel() {

    var uiState by mutableStateOf(State())

    val mutex = Mutex()
    fun event(event: Event) {
        when (event) {
            is Event.LoadInfo -> {
                viewModelScope.launch {
                    uiState = uiState.copy(detail = repo.getInfoById(event.id))
                }

                viewModelScope.launch {
                    val event = repoEvent.getEventByIdDormitories(event.id)
                    uiState = uiState.copy(event = event, tabs = if (event.isNotEmpty()) uiState.tabs + TypeOpportunity.EventNear else uiState.tabs)
                }
            }
            is Event.SelecteTab -> {
                uiState = uiState.copy(selectedTab = event.typeOpportunity)
            }
        }
    }
}