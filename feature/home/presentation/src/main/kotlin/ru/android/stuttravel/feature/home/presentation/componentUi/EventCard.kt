package ru.android.stuttravel.feature.home.presentation.componentUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.android.stuttravel.core.theme.StudentTravelTheme



@Composable
fun EventCard(
    modifier: Modifier,
    image: Painter,
    label: String
) {
    ElevatedCard(
        modifier = modifier

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box {
                Image(
                    painter = image,
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {


            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                    maxLines = 2
                )
            }

        }

    }
}

