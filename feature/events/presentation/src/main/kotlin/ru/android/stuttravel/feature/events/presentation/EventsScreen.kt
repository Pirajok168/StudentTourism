package ru.android.stuttravel.feature.events.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soywiz.klock.DateTime
import com.soywiz.klock.KlockLocale
import com.soywiz.klock.format
import com.soywiz.klock.locale.russian
import ru.android.stuttravel.feature.events.presentation.viewmodel.Events
import ru.android.stuttravel.feature.events.presentation.viewmodel.EventsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    eventsViewModel: EventsViewModel = viewModel(),
    toFiltersScreen: ()-> Unit = {},
    toBack: () -> Unit,
    toCheckInfo: (idEvent: String, idUni: String) -> Unit
) {
    val uiState = eventsViewModel.uiState

    LaunchedEffect(key1 = 0, block = {
        if (uiState.list.isEmpty())
            eventsViewModel.event(Events.LoadEvents)
    })
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars.only(
                        WindowInsetsSides.Top
                    )),
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.TopStart
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .requiredHeight(65.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            AnimatedVisibility(visible = true ) {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(bounded = true)

                                        ) {
                                            toBack()
                                            // eventsViewModel.event(Events.CloseSearch)
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
                                                eventsViewModel.event(Events.Searching(it))
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
                                                ,
                                            decorationBox = {
                                                    innerTextField ->
                                                if (uiState.searchValue.isEmpty()){
                                                    Text(
                                                        text = "Введите город",
                                                    )
                                                }
                                                innerTextField()
                                            }
                                        )
                                        IconButton(
                                            onClick = {
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
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp)
        ){
            items(uiState.list){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            toCheckInfo(it.id, it.idUniversity)
                        }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.photos)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop,

                    )

                    Column(
                        modifier = Modifier.
                                padding(start = 8.dp)
                            .height(120.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = it.type.title,
                            style = MaterialTheme
                                .typography
                                .labelSmall
                                .copy(color = MaterialTheme.colorScheme.primary),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = "Стоимость: ${it.price} рублей",
                            style = MaterialTheme.typography.bodySmall
                                .copy(color = MaterialTheme.colorScheme.outline),
                            maxLines = 2
                        )

                        Text(
                            text = "Дата: ${it.fromDate.toFormatString("dd.MM.yyyy")}" +
                                    " - ${it.toDate.toFormatString("dd.MM.yyyy")}",
                            style = MaterialTheme.typography.bodySmall
                                .copy(color = MaterialTheme.colorScheme.outline),
                            maxLines = 2,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }
        }
    }
}

fun Long.toFormatString(regexDate: String): String{
    val locale = KlockLocale.russian
    return DateTime(this).local.format(format = regexDate, locale = locale)
}
