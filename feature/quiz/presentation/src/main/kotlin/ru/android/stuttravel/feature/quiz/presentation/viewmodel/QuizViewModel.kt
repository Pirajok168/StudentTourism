package ru.android.stuttravel.feature.quiz.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.android.stuttravel.feature.quiz.presentation.Category
import ru.android.stuttravel.feature.quiz.presentation.CountValue
import ru.shared.IRepoUserRecommendation
import ru.shared.MobileSdk
import ru.shared.feature.userReccomendations.data.IRepoUserRecommendation


data class QuizState(
    val countForNature: Int = 0,
    val countForCity: Int = 0,
    val countForCulture: Int = 0,
    val countForBeach: Int = 0,
    val currentQuestion: Int = 0,

    val selectedQuestion: CountValue? = null
)

sealed class Event {
    data class NextQuestion(val currentQuestion: Int) : Event()

    data class SelectAnswer(val choice: CountValue) : Event()

    object End : Event()
}

class QuizViewModel(
    private val repo: IRepoUserRecommendation = MobileSdk.IRepoUserRecommendation
) : ViewModel() {

    var uiState by mutableStateOf(QuizState())
    private var костыль = mutableListOf<Category>()
    fun event(event: Event) {
        when (event) {
            is Event.NextQuestion -> {
                uiState = uiState.copy(currentQuestion = event.currentQuestion + 1)
            }
            is Event.SelectAnswer -> {
                костыль.add(event.choice.typePoints)
                uiState = when (event.choice.typePoints) {
                    Category.Beach -> {
                        uiState.copy(
                            countForBeach = uiState.countForBeach + 10,
                            selectedQuestion = event.choice
                        )
                    }
                    Category.City -> uiState.copy(
                        countForCity = uiState.countForCity + 10,
                        selectedQuestion = event.choice
                    )
                    Category.Culture -> uiState.copy(
                        countForCulture = uiState.countForCulture + 10,
                        selectedQuestion = event.choice
                    )
                    Category.Nature -> uiState.copy(
                        countForNature = uiState.countForNature + 10,
                        selectedQuestion = event.choice
                    )
                }
            }
            Event.End -> {
                val a = костыль.groupBy {
                    it.desc
                }
                val b = a.maxBy { (category, value) ->
                    value.size
                }
                viewModelScope.launch {
                    when (b.key) {
                        Category.City.desc -> {
                            repo.setUserRecommendations(
                                destrict = listOf("ПФО", "УФО"),
                                typeEvent = "proforientation"
                            )
                        }
                        Category.Nature.desc -> {
                            repo.setUserRecommendations(
                                destrict = listOf("ДФО", "СКФО", "СФО"),
                                typeEvent = "scientific"
                            )
                        }
                        Category.Beach.desc -> {
                            repo.setUserRecommendations(
                                destrict = listOf("ДФО", "ЮФО"),
                                typeEvent = "scientific"
                            )
                        }
                        Category.Culture.desc -> {
                            repo.setUserRecommendations(
                                destrict = listOf("СЗФО", "ЦФО"),
                                typeEvent = "cultural"
                            )
                        }

                    }
                }


            }
        }
    }
}