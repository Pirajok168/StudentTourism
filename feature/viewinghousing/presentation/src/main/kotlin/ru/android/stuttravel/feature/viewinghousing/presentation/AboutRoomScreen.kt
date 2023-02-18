package ru.android.stuttravel.feature.viewinghousing.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel.AboutRoomViewModel
import ru.android.stuttravel.feature.viewinghousing.presentation.viewmodel.EventAboutRoom


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun AboutRoomScreen(
    id: String,
    aboutRoomViewModel: AboutRoomViewModel = viewModel(),
    toBack: () -> Unit
) {
    val uiState = aboutRoomViewModel.uiState
    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = Unit, block = {
        aboutRoomViewModel.event(EventAboutRoom.LoadData(id))
    })
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    FilledTonalIconButton(onClick = toBack) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                },
                title = {

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )

            )
        }
    ) {
        Column(
            modifier = Modifier.padding( bottom =it.calculateBottomPadding()),
        ) {
            if (uiState.room != null)
                HorizontalPager(
                    count = uiState.room.photosRooms.size,
                    content = {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(uiState.room.photosRooms[it])
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = uiState.room?.typeRooms.orEmpty(),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = uiState.room?.descriptionRooms.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

        }
    }
}


