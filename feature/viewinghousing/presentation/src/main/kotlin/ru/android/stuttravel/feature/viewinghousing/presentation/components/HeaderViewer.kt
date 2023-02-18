package ru.android.stuttravel.feature.viewinghousing.presentation.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.viewinghousing.presentation.ViewingHouses
import ru.shared.feature.seeInfoDormitories.data.model.PresentationDetailDormitories


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeaderViewer(detail: PresentationDetailDormitories) {
    val content = LocalContext.current
    val pagerState = rememberPagerState()
    Column()
    {
        HorizontalPager(
            count = detail.photos.size,
            content = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(detail.photos[it])
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
    }


    Row(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 16.dp
            )
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top

    ) {
        Text(
            text = detail.name.trim(),

            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .alignByBaseline()
                .padding(end = 8.dp)
                .weight(5f),
            maxLines = 2
        )

        if (detail.reviews.isNotEmpty())
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


    }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Icon(
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = "",
            modifier = Modifier.alpha(0.4f)
        )

        Text(
            text = "Россия, ${detail.city}, ${detail.street} ${detail.houseNumber}",
            style = MaterialTheme.typography.bodyLarge
                .copy(fontWeight = FontWeight.Thin),
            lineHeight = 16.sp
        )
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ElevatedButton(
            onClick = {
                val intent = Intent().apply {
                    action = Intent.ACTION_DIAL
                    data = Uri.parse("tel:${detail.phone}")
                }
                try {
                    content.startActivity(intent)
                } catch (e: ActivityNotFoundException) {

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = "Позвонить")
        }

        ElevatedButton(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {

                    data = Uri.parse(
                        "" +
                                "mailto:${detail.email}"
                    )
                    putExtra(Intent.EXTRA_SUBJECT, detail.name)

                }
                try {
                    content.startActivity(intent)
                } catch (e: ActivityNotFoundException) {

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = "Написать")
        }
    }

}

