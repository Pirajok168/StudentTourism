package ru.android.stuttravel.feature.home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.shared.IEventRepo
import ru.shared.ISearchDormitories
import ru.shared.MobileSdk
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.feature.event.data.IRepoEvent
import ru.shared.feature.event.data.model.PresentationEvent
import ru.shared.feature.home.data.IRepositoryHome
import ru.shared.feature.home.data.model.MostPopular
import ru.shared.feature.home.data.model.RecommendedDormitories


data class HomeState(
    val mostPopular: List<MostPopular> = emptyList(),
    val listEvent: List<PresentationEvent> = emptyList(),
    val loading: Boolean = false,
    val searchValue: String = "",
    val isSearch: Boolean = false,
)

sealed class Event {
    object LoadDisplayData: Event()

    object LoadAll: Event()
    object Search: Event()
    object CloseSearch: Event()

    data class Searching(val value: String): Event()
}


class HomeViewModel  constructor(
) : ViewModel() {
    private val repositoryHome: IRepositoryHome = MobileSdk.ISearchDormitories
    private val repositoryEvent: IRepoEvent = MobileSdk.IEventRepo
    var homeState: HomeState by mutableStateOf(HomeState())
    private val mutex = Mutex()



    fun event(event: Event) {
        when (event) {
            is Event.Search -> homeState = homeState.copy(isSearch = true)
            is Event.Searching -> {


            }
            Event.CloseSearch -> {
                homeState = homeState.copy(isSearch = false)
            }
            Event.LoadAll -> {

            }
            Event.LoadDisplayData -> {
                viewModelScope.launch(Dispatchers.IO) {
                    async {
                        downloadMostPopular()
                    }
                    async {
                        loadEvent()
                    }

                }

            }
        }
    }

    private suspend fun loadEvent(){
        val event = repositoryEvent.getAllEvent()

        homeState = homeState.copy(listEvent = if(event.isNotEmpty()) event.subList(0, 5) else emptyList())
    }


    private suspend fun downloadMostPopular() {



        repositoryHome.getMostPopular()

            .collect {
                mutex.withLock(Dispatchers.Main) {
                    when (it) {
                        is FlowResponse.Error -> {

                        }
                        is FlowResponse.Loading -> {
                            if(!it.isLoading)
                                loadEvent()
                            homeState = homeState.copy(
                                loading = it.isLoading,
                            )
                        }

                        is FlowResponse.Success -> {
                            homeState = homeState.copy(
                                mostPopular = it.data
                            )

                        }

                    }
                }

            }

    }


}