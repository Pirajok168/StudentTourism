package ru.android.stuttravel.feature.news.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.soywiz.klock.DateTime
import com.soywiz.klock.KlockLocale
import com.soywiz.klock.format
import com.soywiz.klock.locale.russian
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.news.presentation.viewmodel.Event
import ru.android.stuttravel.feature.news.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = viewModel(),
    padding: PaddingValues,
) {
    val state = newsViewModel.uiState
    LaunchedEffect(key1 = Unit, block = {
        if (state.listNewsPresentation.isEmpty()){
            newsViewModel.event(Event.LoadNews)
        }
    })
    Scaffold() {
        LazyColumn(
            modifier = Modifier
                .padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(state.listNewsPresentation){
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column() {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.cover)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop,
                        )

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = it.title,
                                style = MaterialTheme
                                    .typography.titleLarge,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = it.createdTimestamp.toFormatString("MMM, d, yyyy"),
                                style = MaterialTheme.typography
                                    .titleSmall
                                    .copy(color = MaterialTheme.colorScheme.outline)
                            )

                            Text(
                                text = it.content,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 8,
                                overflow = TextOverflow.Ellipsis,
                            )


                        }
                        LazyRow(
                           contentPadding = PaddingValues(horizontal = 16.dp),
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        ){
                            items(it.tags) {
                                Surface(
                                    tonalElevation = 8.dp,
                                    shape = MaterialTheme.shapes.large,
                                    modifier = Modifier
                                        .padding(end = 8.dp),
                                    color = MaterialTheme.colorScheme.tertiaryContainer
                                ) {
                                    Text(
                                        text =it,
                                        modifier = Modifier
                                            .padding(8.dp)
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

fun Long.toFormatString(regexDate: String): String{
    val locale = KlockLocale.russian
    return DateTime(this).local.format(format = regexDate, locale = locale)
}

