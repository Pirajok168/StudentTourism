package ru.android.stuttravel.feature.viewinghousing.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.stuttravel.feature.viewinghousing.presentation.components.*
import ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel.Event
import ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel.TypeOpportunity
import ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel.ViewModelInfo



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewingHouses(
    parentPaddingValues: PaddingValues = PaddingValues(),
    idDormitoies: String,
    viewModelInfo: ViewModelInfo = viewModel(),
    onBack: () -> Unit = {},
    toCheckInfoRoom: (id: String) -> Unit,
    toCheckEvent: (idEvent: String, idUni: String) -> Unit,
    toBooking: (idDormitroie: String) -> Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModelInfo.event(Event.LoadInfo(idDormitoies))
    })

    val uiState = viewModelInfo.uiState

    val mainScrollState = rememberScrollState()
    val selectedTab = uiState.selectedTab
    val content = LocalContext.current
    Scaffold(
        modifier = Modifier
            .padding(parentPaddingValues),
        contentWindowInsets = WindowInsets.systemBars.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
        ),
        topBar = {

            CenterAlignedTopAppBar(
                navigationIcon = {
                    FilledTonalIconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                },
                actions = {

                    FilledTonalIconButton(onClick = {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Привет")
                            putExtra(Intent.EXTRA_SUBJECT, "Привет2")
                            type = "*/*"
                        }
                        try {
                            content.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {

                        }
                    }) {
                        Icon(imageVector = Icons.Outlined.Share, contentDescription = "")
                    }
                },
                title = {

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )

                )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .windowInsetsPadding(
                        WindowInsets
                            .navigationBars.only(
                                WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                            )
                    )

            ) {
                ElevatedButton(
                    onClick = {
                        toBooking(idDormitoies)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Оставить заявку")
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
        ) {
            item {
                if (uiState.detail != null)
                    HeaderViewer(
                        uiState.detail
                    )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }



            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Возможности",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )


                    ScrollableTabRow(
                        selectedTabIndex = selectedTab.index,
                        tabs = {
                            uiState.tabs.forEachIndexed { index, item ->
                                val animate by animateDpAsState(
                                    if (selectedTab.index == index) 12.dp else 1.dp,
                                    animationSpec = tween(
                                        easing = LinearOutSlowInEasing,
                                    )
                                )
                                ElevatedCard(
                                    modifier = Modifier
                                        .padding(
                                            start = 16.dp, top = 16.dp, bottom = 24.dp,
                                            end = if (index == uiState.tabs.count() - 1) 12.dp else 1.dp
                                        )

                                        .height(50.dp),
                                    onClick = {
                                        viewModelInfo.event(Event.SelecteTab(item))
                                    },
                                    elevation = CardDefaults.elevatedCardElevation(
                                        defaultElevation = animate
                                    ),

                                    ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = ""
                                            )

                                            Text(text = item.label)
                                        }
                                    }

                                }
                            }

                        },
                        divider = {},
                        indicator = {},
                        edgePadding = 0.dp,
                    )

                    when (selectedTab) {
                        TypeOpportunity.Rooms -> {
                            RoomsViewer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                uiState.detail?.rooms ?: emptyList(),
                                toCheckInfoRoom
                            )
                        }

                        TypeOpportunity.EventNear -> {
                            EventsViewer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                uiState.event,
                                toCheckEvent = { id, idUni->
                                    toCheckEvent(id, idUni)
                                }
                            )
                        }

                        TypeOpportunity.Services -> {
                            ServicesViewer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                uiState.detail?.services.orEmpty()
                            )
                        }

                        TypeOpportunity.Conditions -> {
                            if (uiState.detail != null)
                                ConditionsViewer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    uiState.detail
                                )
                        }

                    }


                }
            }


        }
    }
}


