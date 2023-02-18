package ru.android.stuttravel.feature.profile.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.profile.presentation.componentetsUi.AdditionalInformationCard
import ru.android.stuttravel.feature.profile.presentation.componentetsUi.PersonalInformationCard
import ru.android.stuttravel.feature.profile.presentation.viewmodel.Event
import ru.android.stuttravel.feature.profile.presentation.viewmodel.LevelNotice
import ru.android.stuttravel.feature.profile.presentation.viewmodel.ProfileViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    padding: PaddingValues,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val uiState = profileViewModel.uiState

    LaunchedEffect(key1 = Unit, block = {
        profileViewModel.event(Event.LoadData)
    })
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(padding)

            ,


        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = "Профиль")
                },
                scrollBehavior = scrollBehavior,
                windowInsets = WindowInsets.systemBars.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                )
            )
        }
    ) {
        LazyColumn(

            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()



                ,
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(110.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    // TODO: Пока заглушка
                    Surface(
                        modifier = Modifier

                            .requiredSize(100.dp),
                        shape = CircleShape,

                        tonalElevation = 5.dp,

                        ) {
                        Image(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "",
                            modifier = Modifier.padding(20.dp)
                        )


                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .weight(1.5f),
                        text = "Сделайте фотографию или выберите фотографию из галереи ",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.outline,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Surface(
                        modifier = Modifier

                            .requiredSize(40.dp),
                        onClick = { },
                        tonalElevation = 20.dp,
                        shadowElevation = 20.dp,
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Camera,
                            contentDescription = "df",
                            modifier = Modifier.requiredSize(24.dp)
                        )
                    }

                }

            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
                ) {
                    items( uiState.listWarning.sortedBy { it.levelNotice }) { event ->
                        Card(
                            Modifier
                                .size(width = 100.dp, height = 110.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = when (event.levelNotice){
                                    LevelNotice.Important -> MaterialTheme.colorScheme.errorContainer
                                    LevelNotice.Usually -> MaterialTheme.colorScheme.tertiaryContainer
                                },
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),

                            ) {

                                Icon(imageVector = event.icon, contentDescription = "")

                                Text(
                                    text = event.title,
                                    style = MaterialTheme.typography.bodySmall.copy(),
                                    modifier = Modifier.padding(top = 6.dp)
                                )
                            }

                        }
                    }
                }
            }

            item {
                Text(
                    text = "Личная информация",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 26.sp
                    )
                )
            }

            item {
                if (uiState.profile!=null)
                PersonalInformationCard(
                    fio = "${uiState.profile.lastName} ${uiState.profile.firstName} ${uiState.profile.middleName}",
                    phone = uiState.profile.phone,
                    email = uiState.profile.email,
                    birthday = uiState.profile.birthday
                )
            }

            item {
                Text(
                    text = "Дополнительная информация",
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 26.sp
                    )
                )
            }

            item {
                if (uiState.profile != null) {
                    AdditionalInformationCard(
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp, bottom = 32.dp)
                            .imePadding(),
                        role = uiState.profile.studentRoleType,
                        city = uiState.profile.departureCity,
                        univ = uiState.profile.universityName,
                        specialization = ""

                    )
                }
            }


        }
    }
}


@Preview()
@Composable
fun PrevHome() {
    StudentTravelTheme() {
        val profileViewModel: ProfileViewModel = viewModel()
        ProfileScreen(padding = PaddingValues(), profileViewModel)
    }
}

