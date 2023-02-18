package ru.android.stuttravel.feature.home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.shared.IEventRepo
import ru.shared.IRepoFilters
import ru.shared.ISearchDormitories
import ru.shared.MobileSdk
import ru.shared.core.model.wrapper.FlowResponse
import ru.shared.core.model.wrapper.ResponseError
import ru.shared.core.model.wrapper.ResponseRequest
import ru.shared.feature.event.data.IRepoEvent
import ru.shared.feature.event.data.model.PresentationEvent
import ru.shared.feature.filters.data.IRepoFilters
import ru.shared.feature.home.data.IRepositoryHome
import ru.shared.feature.home.data.model.MostPopular
import ru.shared.feature.home.data.model.RecommendedDormitories


data class HomeState(
    val mostPopular: List<MostPopular> = emptyList(),
    val listEvent: List<PresentationEvent> = emptyList(),
    val loading: Boolean = false,
    val searchValue: String = "",
    val isSearch: Boolean = false,
    val recommendedDormitories: List<RecommendedDormitories> = emptyList(),
    val isErrorRecommended: ResponseError? = null
)

sealed class Event {
    object LoadDisplayData: Event()

    object LoadAll: Event()
    object Search: Event()
    object CloseSearch: Event()
    object LoadRecommended: Event()
    data class Searching(val value: String): Event()
}


class HomeViewModel  constructor(
) : ViewModel() {
    private val repositoryHome: IRepositoryHome = MobileSdk.ISearchDormitories
    private val repositoryEvent: IRepoEvent = MobileSdk.IEventRepo
    private val filters: IRepoFilters = MobileSdk.IRepoFilters
    var homeState: HomeState by mutableStateOf(HomeState())
    private val mutex = Mutex()

    val filteredList = filters.filteredData
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun event(event: Event) {
        when (event) {
            is Event.Search -> homeState = homeState.copy(isSearch = true)
            is Event.Searching -> {
                homeState = homeState.copy(searchValue = event.value)
                viewModelScope.launch(Dispatchers.IO) {
                    filters.searchByCity(event.value)
                }

            }
            Event.CloseSearch -> {
                homeState = homeState.copy(isSearch = false)
            }
            Event.LoadAll -> {
                viewModelScope.launch(Dispatchers.IO) { filters.loadAllDataDormitories() }
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
            Event.LoadRecommended -> viewModelScope.launch {
                loadRecommendation()
            }
        }
    }
    private suspend fun loadRecommendation() = withContext(Dispatchers.IO){
        homeState = when(val  recommendedDormitories = repositoryHome.getUserRecommendations()){
            is ResponseRequest.Error -> withContext(Dispatchers.Main) {
                homeState.copy(
                    isErrorRecommended = recommendedDormitories.error
                )
            }
            is ResponseRequest.Success ->   withContext(Dispatchers.Main) { homeState.copy(recommendedDormitories = recommendedDormitories.data)}
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
                            if(!it.isLoading) {
                                loadEvent()
                            }
                            homeState = homeState.copy(
                                loading = it.isLoading,
                            )
                        }

                        is FlowResponse.Success -> {
                            viewModelScope.launch(Dispatchers.IO) {
                                filters.loadAllDataDormitories()

                                loadRecommendation()
                            }
                            homeState = homeState.copy(
                                mostPopular = it.data
                            )

                        }

                    }
                }

            }

    }


}