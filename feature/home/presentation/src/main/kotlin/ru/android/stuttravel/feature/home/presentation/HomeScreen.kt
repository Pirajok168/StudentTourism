package ru.android.stuttravel.feature.home.presentation


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.placeholder.material.placeholder
import ru.android.stutravel.feature.filters.presentation.SortedScreen
import ru.android.stuttravel.feature.home.presentation.componentUi.EventCard
import ru.android.stuttravel.feature.home.presentation.componentUi.PopulationPlace
import ru.android.stuttravel.feature.home.presentation.componentUi.WhatIsLookCard
import ru.android.stuttravel.feature.home.presentation.viewmodel.Event
import ru.android.stuttravel.feature.home.presentation.viewmodel.HomeViewModel

data class WhatIsLook(
    val label: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    padding: PaddingValues = PaddingValues(),
    homeViewModel: HomeViewModel = viewModel(),
    toViewAboutHouse: (idDormitories: String) -> Unit = {},
    toFiltersScreen: () -> Unit,
    toEventsScreen: () -> Unit,
    toNewsScreen: () -> Unit,
    toViewAboutEvent: (idEvent: String, idUni: String) -> Unit
) {
    val uiState = homeViewModel.homeState
    val list by homeViewModel.filteredList.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = Unit, block = {
        if (uiState.mostPopular.isEmpty()) {
            homeViewModel.event(Event.LoadDisplayData)
        }

        if (uiState.isErrorRecommended == null && uiState.recommendedDormitories.isEmpty()) {
            homeViewModel.event(Event.LoadRecommended)
        }

        if (list.isEmpty()) {
            homeViewModel.event(Event.LoadAll)
        }

    })
    val listIcon = listOf<WhatIsLook>(
        WhatIsLook(label = "??????????", icon = Icons.Outlined.Science),
        WhatIsLook(label = "??????????", icon = Icons.Outlined.House),
        WhatIsLook(label = "??????????????", icon = Icons.Outlined.EventAvailable),
        WhatIsLook(label = "??????????????", icon = Icons.Outlined.Newspaper),
    )


    val focusManager = LocalFocusManager.current


    Scaffold(
        modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .statusBarsPadding(),

        contentWindowInsets = WindowInsets.statusBars.only(
            WindowInsetsSides.Horizontal
        ),
        topBar = {

            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = if (uiState.isSearch) Alignment.TopStart else Alignment.Center
                ) {
                    Column(
                        verticalArrangement = if (uiState.isSearch) Arrangement.Top else Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        /* AnimatedVisibility(visible = !isSearching) {
                             Column(
                                 modifier = Modifier.fillMaxWidth(),
                                 horizontalAlignment = Alignment.CenterHorizontally
                             ) {
                                 Text(
                                     text = "?????????? ????????",
                                     style = MaterialTheme.typography.displaySmall,
                                     textAlign = TextAlign.Center,
                                     fontWeight = FontWeight.Thin,
                                     modifier = Modifier.offset(y = (15).dp),
                                     color = MaterialTheme.colorScheme.outline,
                                     fontSize = 26.sp
                                 )
                                 Text(
                                     text = "??????????????????????".uppercase(),
                                     style = MaterialTheme.typography.displayMedium,
                                     fontWeight = FontWeight.Bold,
                                     fontSize = 36.sp
                                 )
                             }

                         }*/

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .requiredHeight(65.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            AnimatedVisibility(visible = uiState.isSearch) {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(bounded = true)

                                        ) {
                                            homeViewModel.event(Event.CloseSearch)
                                            focusManager.clearFocus()
                                        }
                                        .padding(end = 16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowBack,
                                        contentDescription = ""
                                    )
                                }

                            }
                            ElevatedCard(
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                elevation = CardDefaults.elevatedCardElevation(
                                    defaultElevation = 8.dp,
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            horizontal = 16.dp,
                                            vertical = 8.dp
                                        ),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Search,
                                            "",
                                            modifier = Modifier
                                                .padding(end = 8.dp)
                                                .weight(1f)
                                        )
                                        BasicTextField(
                                            value = uiState.searchValue,
                                            onValueChange = {
                                                homeViewModel.event(Event.Searching(it))
                                            },
                                            textStyle = TextStyle(
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 16.sp,
                                                color = MaterialTheme.colorScheme.onSurface
                                            ),
                                            singleLine = true,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(7f)
                                                .onFocusChanged {
                                                    if (it.hasFocus)
                                                        homeViewModel.event(Event.Search)
                                                },
                                            decorationBox = { innerTextField ->
                                                if (uiState.searchValue.isEmpty()) {
                                                    Text(
                                                        text = "?????????????? ??????????",
                                                    )
                                                }
                                                innerTextField()
                                            }
                                        )
                                        IconButton(
                                            onClick = {
                                                homeViewModel.event(Event.Search)
                                                toFiltersScreen()
                                            },
                                            modifier = Modifier
                                                .padding(start = 8.dp)
                                                .weight(1f)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Outlined.FilterAlt,
                                                "",


                                                )
                                        }

                                    }

                                }

                            }
                        }


                    }

                }
            }
        }

    ) {

        if (!uiState.isSearch) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = it
            ) {


                item {
                    Text(
                        text = "???????????????????? ?????? ??????",
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        if (uiState.recommendedDormitories.isNotEmpty()) {
                            items(uiState.recommendedDormitories) {
                                PopulationPlace(
                                    Modifier
                                        .size(190.dp, 260.dp),
                                    title = it.name,
                                    city = it.city.orEmpty(),
                                    photoUrl = it.photo,
                                    racing = "",
                                    onClick = {
                                        toViewAboutHouse(it.idDormitories)
                                    },

                                    )
                            }
                        } else {
                            items(5) {
                                Surface(
                                    modifier = Modifier.size(
                                        190.dp, 260.dp
                                    )
                                        .placeholder(
                                            visible = true,
                                            shape = MaterialTheme.shapes.medium
                                        ),
                                    shape = MaterialTheme.shapes.medium
                                ){

                                }
                            }

                        }

                    }
                }

                item {
                    Text(
                        text = "???????????????? ??????????????????????",
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                }



                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        if(uiState.mostPopular.isNotEmpty()){
                            items(uiState.mostPopular, key = { it.city }) {
                                PopulationPlace(
                                    Modifier
                                        .size(190.dp, 260.dp),
                                    title = it.title,
                                    city = it.city,
                                    photoUrl = it.image,
                                    racing = it.rating.toString(),
                                    onClick = {
                                        toViewAboutHouse(it.idDormitories)
                                    }
                                )
                            }
                        }else{
                            items(5) {
                                Surface(
                                    modifier = Modifier.size(
                                        190.dp, 260.dp
                                    )
                                        .placeholder(
                                            visible = true,
                                            shape = MaterialTheme.shapes.medium
                                        ),
                                    shape = MaterialTheme.shapes.medium
                                ){

                                }
                            }
                        }

                    }


                    /* LinearProgressIndicator(
                         progress = animateFloat,
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(horizontal = 16.dp),
                         strokeCap = StrokeCap.Round
                     )*/

                }

                item {
                    Text(
                        text = "?????? ?????????????????????",
                        modifier = Modifier
                            .padding(top = 32.dp, bottom = 16.dp)
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                }

                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        listIcon.forEach {
                            WhatIsLookCard(label = it.label, icon = it.icon) {
                                when (it.label) {
                                    "??????????????" -> {
                                        toEventsScreen()
                                    }
                                    "??????????" -> {
                                        homeViewModel.event(Event.Search)
                                    }
                                    "??????????????" -> {
                                        toNewsScreen()
                                    }
                                }
                            }
                        }

                    }
                }

                item {
                    Text(
                        text = "C????????????",
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                }

                item {
                    LazyRow(
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if(uiState.listEvent.isNotEmpty()){
                            items(uiState.listEvent) {
                                EventCard(modifier = Modifier
                                    .size(230.dp, 230.dp),
                                    image = it.photos,
                                    label = it.name,
                                    it.type,
                                    toViewAboutEvent = {
                                        toViewAboutEvent(it.id, it.idUniversity)
                                    }
                                )
                            }
                        }else{
                            items(5) {
                                Surface(
                                    modifier = Modifier.size(
                                        190.dp, 260.dp
                                    )
                                        .placeholder(
                                            visible = true,
                                            shape = MaterialTheme.shapes.medium
                                        ),
                                    shape = MaterialTheme.shapes.medium
                                ){

                                }
                            }
                        }

                    }

                }


            }
        } else {
            SortedScreen(it, list, toViewAboutHouse)

        }
    }
}


