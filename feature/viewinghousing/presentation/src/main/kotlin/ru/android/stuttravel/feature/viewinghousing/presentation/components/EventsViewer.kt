package ru.android.stuttravel.feature.viewinghousing.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.viewinghousing.presentation.ViewingHouses
import ru.shared.feature.event.data.model.RelatingEventDormitories


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsViewer(
    modifier: Modifier = Modifier,
    events: List<RelatingEventDormitories>,
    toCheckEvent: (id: String, idUni: String) -> Unit
) {
    Box(
        modifier = modifier

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            events.forEach {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(bottom = 8.dp)
                        .clickable { toCheckEvent(it.id, it.idUniversity) }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ){
                        Row{
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.photos.firstOrNull())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop,
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = it.type.title,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Text(
                                    text = it.name,
                                    style = MaterialTheme.typography.labelLarge,
                                    maxLines = 2
                                )
                                Text(
                                    text = "Стоимость ${it.price} рублей",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }


                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ){
                        Icon(imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "")
                    }
                }

            }


        }

    }
}


