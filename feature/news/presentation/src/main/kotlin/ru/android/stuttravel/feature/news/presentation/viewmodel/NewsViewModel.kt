package ru.android.stuttravel.feature.news.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.shared.IRepoNews
import ru.shared.MobileSdk
import ru.shared.feature.news.data.IRepoNews
import ru.shared.feature.news.data.RepoNewsImpl
import ru.shared.feature.news.data.model.NewsPresentation

data class NewsState(
    val listNewsPresentation: List<NewsPresentation> = emptyList()
)

sealed class Event{
    object LoadNews: Event()
}

class NewsViewModel(
    private val repoNewsImpl: IRepoNews = MobileSdk.IRepoNews
): ViewModel() {
    var uiState by mutableStateOf(NewsState())

    fun event(event: Event){
        when(event){
            Event.LoadNews ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val data = repoNewsImpl.getAllNews()
                    withContext(Dispatchers.Main){
                        uiState = uiState.copy(listNewsPresentation = data.subList(0, 10))
                    }
                }
            }
        }
    }
}