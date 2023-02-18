package ru.android.stuttravel.feature.events.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shared.IEventRepo
import ru.shared.MobileSdk
import ru.shared.feature.event.data.IRepoEvent
import ru.shared.feature.event.data.model.PresentationEvent


data class State(
    val list: List<PresentationEvent> = listOf(),
    val searchValue: String = ""
)

sealed class Events{
    object LoadEvents: Events()
    data class Searching(val input: String): Events()
}

class EventsViewModel(
    private val repoEvent: IRepoEvent = MobileSdk.IEventRepo
): ViewModel() {

    var uiState by mutableStateOf(State())

    fun event(events: Events){
        when(events){
            Events.LoadEvents -> {
                viewModelScope.launch(Dispatchers.IO){
                    val data = repoEvent.getAllEvent()

                    withContext(Dispatchers.Main){
                        uiState = uiState.copy(list = data)
                    }
                }
            }
            is Events.Searching -> {
                uiState = uiState.copy(searchValue = events.input)
//                viewModelScope.launch(Dispatchers.IO) {
//                    repositorySearchDormitoriesImpl.searchByCity(event.value)
//                }

            }

        }
    }
}