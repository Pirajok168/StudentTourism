package ru.android.stutravel.feature.filters.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.commons.feature.search.data.database.GetAllFilters


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortedScreen(
    paddingValues: PaddingValues = PaddingValues(),
    value: List<GetAllFilters>,
    onClickInfo: (id: String) -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(value,) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                onClick = {
                    onClickInfo(it.idDormitories!!)
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.photos?.firstOrNull())
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )


                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomStart,
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),

                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = it.name.orEmpty(),
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Start,
                                    fontFamily = FontFamily.SansSerif,
                                    maxLines = 2,

                                    )

                                Text(
                                    text = it.nameUniversity,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Start,
                                    overflow = TextOverflow.Ellipsis,


                                    maxLines = 1,

                                    )
                                
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(top = 4.dp, end = 6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Hotel,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(end = 2.dp)
                                            .size(12.dp)
                                            .alpha(0.5f)


                                    )
                                    Text(
                                        text = "от ${it.minDays} до ${it.maxDays} дней",
                                        style = MaterialTheme
                                            .typography
                                            .bodySmall,
                                        fontWeight = FontWeight.Thin,
                                        modifier = Modifier

                                    )
                                }



                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier//.padding(top = 4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(end = 2.dp)
                                            .size(12.dp)
                                            .alpha(0.5f)


                                    )
                                    Text(
                                        text = it.city.orEmpty(),
                                        style = MaterialTheme
                                            .typography
                                            .bodySmall,
                                        fontWeight = FontWeight.Thin,
                                        modifier = Modifier

                                    )
                                }
                            }

                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.9f),
                            modifier = Modifier
                                .padding(16.dp),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "1200-1300 р.",
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 6.dp)

                            )
                        }
                    }
                }


            }

        }
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    StudentTravelTheme() {
//        SortedScreen(value = list.value)
//    }
//}