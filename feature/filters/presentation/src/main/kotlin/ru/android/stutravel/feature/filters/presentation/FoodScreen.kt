package ru.android.stutravel.feature.filters.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.android.stutravel.feature.filters.presentation.viewmodel.Event
import ru.android.stutravel.feature.filters.presentation.viewmodel.FilterSearchViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FoodScreen(
    filterSearchViewModel: FilterSearchViewModel,
    onBack: () -> Unit = {}
) {
    val uiState = filterSearchViewModel.uiState
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Выберите тип питания")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                },
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues( vertical = 16.dp)
        ) {




                items(uiState.listAvailableTypeFood, key = { item -> item  }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .animateItemPlacement(),
                        onClick = {
                            filterSearchViewModel.event(Event.SelectTypeFood(it))
                            onBack()
                        },
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = it.title,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }
                }





        }
    }
}
