package ru.android.stuttravel.feature.quiz.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.quiz.presentation.viewmodel.Event
import ru.android.stuttravel.feature.quiz.presentation.viewmodel.QuizViewModel

sealed class Category( val desc: String){
    object Culture: Category("Культура")
    object Beach: Category("Пляж")
    object City: Category("Город")
    object Nature: Category("Природа")
}

data class CountValue(
    val question: String,
    val typePoints: Category
)

sealed class QuizType(val labelQuestion: String, val listQuiz: List<CountValue>) {
    object First : QuizType(
        "Что вы хотели бы делать в путешествии?",
        listOf(
            CountValue("Насладиться тишиной природы", Category.Nature),
            CountValue("Посетить исторический центр города", Category.City),
            CountValue("Отдохнуть на пляже", Category.Beach),
            CountValue("Посетить музеи и культурные достопримечательности", Category.Culture)
        )
    )

    object Second : QuizType(
        "Какую одежду вы хотели бы носить во время поездки?",
        listOf(
            CountValue("Купальник", Category.Beach),
            CountValue("Джинсы и футболку", Category.City),
            CountValue("Теплый свитер и штаны", Category.Nature),
            CountValue("Вечернее платье", Category.Culture)
        )
    )

    object Third : QuizType(
        "Где бы вы хотели жить в путешествии?",
        listOf(
            CountValue("На турбазе в окружении высоких гор", Category.Nature),
            CountValue("В уютном отеле с отличным видом", Category.City),
            CountValue("В исторически значимом здании", Category.Culture),
            CountValue("В санатории", Category.Beach)
        )
    )

    object Fourth : QuizType(
        "Если бы погоду в путешествии можно было выбирать, то какую бы вы предпочли?",
        listOf(
            CountValue("Мне нравится, когда за окном моросит дождик", Category.Nature),
            CountValue("Когда тепло и дует лёгкий ветерок", Category.City),
            CountValue("Мечтаю о жарких и солнечных днях", Category.Beach),
            CountValue("Лишь бы не было дождя и сильного ветра", Category.Culture)
        )
    )

    object Fifth : QuizType(
        "Что бы вы взяли с собой в путешествие?",
        listOf(
            CountValue("Книгу", Category.Culture),
            CountValue("Огниво", Category.Nature),
            CountValue("Очки для ныряния", Category.Beach),
            CountValue("Модная одежда", Category.City)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel = viewModel(),
    onEnd: () -> Unit
) {
    val uiState = quizViewModel.uiState
    val listQuiz = listOf(QuizType.First, QuizType.Second, QuizType.Third, QuizType.Fourth, QuizType.Fifth)
    Scaffold() {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column() {
                Text(
                    text = listQuiz[uiState.currentQuestion].labelQuestion,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displaySmall
                )


                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(listQuiz[uiState.currentQuestion].listQuiz) {
                        ElevatedCard(
                            onClick = {
                                      quizViewModel.event(Event.SelectAnswer(it))
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = if (uiState.selectedQuestion == it) MaterialTheme.colorScheme.primary
                             else MaterialTheme.colorScheme.surface
                            )

                            ) {
                            Text(
                                text = it.question,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if((uiState.currentQuestion+1) != listQuiz.size){
                        FilledTonalIconButton(
                            onClick = {
                                quizViewModel.event(Event.NextQuestion(uiState.currentQuestion))
                            }
                        ) {
                            Icon(imageVector = Icons.Outlined.ArrowForwardIos, contentDescription = "")
                        }
                    }else{
                        FilledTonalIconButton(
                            onClick = {
                                quizViewModel.event(Event.End)
                                onEnd()
                            }
                        ) {
                            Icon(imageVector = Icons.Outlined.Done, contentDescription = "")
                        }
                    }

                }
            }


        }
    }
}


