package ru.android.stuttravel.feature.viewinghousing.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.shared.feature.seeInfoDormitories.data.model.PresentationRoomsDetail


@Composable
fun RoomsViewer(
    modifier: Modifier = Modifier,
    rooms: List<PresentationRoomsDetail>,
    toCheckInfoRoom: (id: String) -> Unit
) {
    Box(
        modifier = modifier

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            rooms.forEach{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(bottom = 8.dp)
                        .clickable { toCheckInfoRoom(it.idRooms) }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.photosRooms.firstOrNull())
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
                            .padding(start = 8.dp)
                            .alignByBaseline(),
                        verticalArrangement = Arrangement.SpaceAround

                    ) {

                        Text(
                            text = "Тип комнаты:",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                        )

                        Text(
                            text = "Количество:",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                        )

                        Text(
                            text = "Стоимость:",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 8.dp)
                            .alignByBaseline(),
                        verticalArrangement = Arrangement.SpaceAround

                    ) {

                        Text(
                            text = it.typeRooms,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                        )

                        Text(
                            text = it.amountRooms,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                        )

                        Text(
                            text = it.priceRooms,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                        )
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

