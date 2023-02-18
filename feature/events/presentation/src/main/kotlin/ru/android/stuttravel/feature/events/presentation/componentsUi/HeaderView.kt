package ru.android.stuttravel.feature.events.presentation.componentsUi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ru.android.stuttravel.feature.events.presentation.toFormatString
import ru.shared.feature.event.data.model.DetailEvent


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ColumnScope.HeaderView(events: DetailEvent) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = events.photos.size,
        content = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(events.photos[it])
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
            )
        },
        state = pagerState,
    )

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier

            .align(Alignment.CenterHorizontally)
            .padding(16.dp),
    )
    Row(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 8.dp
            )
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top

    ) {
        Text(
            text = events.name,

            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .alignByBaseline()
                .padding(end = 8.dp)
                .weight(5f),
            maxLines = 2
        )

        /*if (detail.reviews.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .alignByBaseline()
                    .weight(1f),
                verticalAlignment = Alignment.Bottom

            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "",
                    tint = Color(0xFFF0DC31),
                    modifier = Modifier

                )
                Text(
                    text = (detail.reviews.sumOf { it.rating } / detail.reviews.count()).toString(),
                    modifier = Modifier

                )
            }
        }*/

    }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = events.type.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }

    Text(
        text = "Университет: ${events.nameUnit}",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 16.dp)
    )


    Text(
        text = "Город: ${events.city}",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Text(
        text = "Стоимость: ${events.price}",
        style = MaterialTheme.typography.bodySmall
            .copy(color = MaterialTheme.colorScheme.outline),
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Text(
        text = "Дата: ${events.fromDate.toFormatString("dd.MM.yyyy")}" +
                "- ${events.toDate.toFormatString("dd.MM.yyyy")}",
        style = MaterialTheme.typography.bodySmall
            .copy(color = MaterialTheme.colorScheme.outline),
        maxLines = 2,
        fontWeight = FontWeight.Light,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

}