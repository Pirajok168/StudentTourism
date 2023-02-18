package ru.android.stutravel.feature.filters.presentations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.stutravel.feature.filters.presentations.viewmodel.Event
import ru.android.stutravel.feature.filters.presentations.viewmodel.FilterSearchViewModel

enum class FederalDistrict(val title: String) {
    None("Не выбрано"),
    FarEastern("Дальневосточный"),
    Volga("Приволжский"),
    Northwestern("Северо-Западный"),
    NorthCaucasian("Северо-Кавказский"),
    Siberian("Сибирский"),
    Ural("Уральский"),
    Central("Центральный"),
    Southern("Южный")
}



sealed class TypeSortRating(val title: String) {
    object ByRise : TypeSortRating("По возрастанию")
    object ByDownRise : TypeSortRating("По убыванию")
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(
    parentPaddingValues: PaddingValues = PaddingValues(),
    filterSearchViewModel: FilterSearchViewModel = viewModel(),
    backHome: () -> Unit,
    toRegionScreen: () -> Unit,
    toCitiesScreen: () -> Unit,
    toFoodScreen: () -> Unit,
    toUniversitiesScreen: () -> Unit,
    toTypeRoomScreen: () -> Unit,
) {
    val uiState = filterSearchViewModel.uiState
    LaunchedEffect(key1 = 0, block = {
        if (uiState.listAvailableCities.isEmpty())
            filterSearchViewModel.event(Event.LoadAllShortNameUniversities)
    })


    val listTypeSortRating = listOf(TypeSortRating.ByRise, TypeSortRating.ByDownRise)
    var selectedSort by remember {
        mutableStateOf(listTypeSortRating.first())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Фильтры") },
                navigationIcon = {
                    IconButton(onClick = backHome) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
        bottomBar = {
            ElevatedButton(
                onClick = { filterSearchViewModel.event(Event.SaveFilter) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom))
            ) {
                Text(text = "Показать жильё")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Питание",
                    modifier = Modifier
                        .padding(top = 32.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 26.sp
                )

                Surface(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    tonalElevation = 16.dp,
                    onClick = toFoodScreen
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.selectedTypeFood.title.takeIf { it.isNotEmpty() }?:"Не выбрано",
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(6f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline
                        )


                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .weight(1f)
                        )


                    }


                }


                Text(
                    text = "Регион",
                    modifier = Modifier
                        .padding(top = 32.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 26.sp
                )

                Surface(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    tonalElevation = 16.dp,
                    onClick = toRegionScreen
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.selectedRegion.takeIf { it.isNotEmpty() }?:"Не выбрано",
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(6f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline
                        )


                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .weight(1f)
                        )


                    }


                }



                Text(
                    text = "Населенный пункт",
                    modifier = Modifier
                        .padding(top = 32.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 26.sp
                )

                Surface(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    tonalElevation = 16.dp,
                    onClick = toCitiesScreen
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.selectedCity.takeIf { it.isNotEmpty() }?:"Не выбрано",
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(6f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .weight(1f)
                        )

                    }


                }


                Text(
                    text = "Университет",
                    modifier = Modifier
                        .padding(top = 32.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 26.sp
                )

                Surface(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    tonalElevation = 16.dp,
                    onClick = toUniversitiesScreen
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.selectedNameUniversity.takeIf { it.isNotEmpty() }?:"Не выбрано",
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(6f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .weight(1f)
                        )

                    }


                }


                Text(
                    text = "Тип размещения",
                    modifier = Modifier
                        .padding(top = 32.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 26.sp
                )

                Surface(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    tonalElevation = 16.dp,
                    onClick = toTypeRoomScreen
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.selectedTypeRoom.takeIf { it.isNotEmpty() }?:"Не выбрано",
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(6f),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp)
                                .weight(1f)
                        )

                    }


                }


                Text(
                    text = "Сортировка по рейтингу",
                    modifier = Modifier
                        .padding(top = 32.dp),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 26.sp
                )

                Column(Modifier.selectableGroup()) {
                    listTypeSortRating.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = it == selectedSort, onClick = {
                                    selectedSort = it
                                })

                            Text(it.title)
                        }
                    }
                }
            }

        }
    }
}


