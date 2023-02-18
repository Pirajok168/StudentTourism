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
fun CitiesScreen(
    filterSearchViewModel: FilterSearchViewModel,
    onBack: () -> Unit = {}
) {
    val uiState = filterSearchViewModel.uiState
    val grouped = uiState.listAvailableCities.groupBy { it.first() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = filterSearchViewModel.uiState.searchValueCities,
                        onValueChange = {
                            filterSearchViewModel.event(Event.SearchCities(it))
                        },
                        singleLine = true,
                        colors = TextFieldDefaults
                            .textFieldColors(
                                containerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                        placeholder = {
                            Text("Введите город")
                        }
                    )
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


            grouped.forEach { (key, value) ->

                stickyHeader {
                    Surface(tonalElevation = 6.dp, modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
                        Text(
                            text = key.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding( horizontal = 16.dp)
                        )
                    }

                }

                items(value, key = { item -> item  }) {
                    Surface(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).animateItemPlacement(),
                        onClick = {
                            filterSearchViewModel.event(Event.SelectCities(it))
                            onBack()
                        },
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        )
                    }
                }


            }
        }
    }
}
