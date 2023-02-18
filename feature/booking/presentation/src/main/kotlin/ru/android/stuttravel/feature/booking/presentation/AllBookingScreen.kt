package ru.android.stuttravel.feature.booking.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.android.stuttravel.core.theme.StudentTravelTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllBookingScreen(paddingValues: PaddingValues) {
    Scaffold(
        modifier = Modifier.padding(paddingValues),
        contentWindowInsets = WindowInsets.statusBars.only(
            WindowInsetsSides.Horizontal
        ), topBar = {
            TopAppBar(
                title = {
                    Text("Бронирования")
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "ФГБОУ ВО ВСГИК Студгородок Общежитие",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.widthIn(max = 210.dp)
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 6.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(18.dp)
                                )
                                Text(
                                    text = "Улан-Удэ",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column() {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {

                                        Icon(
                                            imageVector = Icons.Outlined.CalendarMonth,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(18.dp)
                                        )

                                        Text(
                                            text = "Даты",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }


                                    Text(
                                        text = "11.08.2022 - 20.08.2022",
                                        style = MaterialTheme.typography.titleSmall.copy(),

                                        )
                                }

                                Column() {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {

                                        Icon(
                                            imageVector = Icons.Outlined.CurrencyRuble,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(18.dp)
                                        )

                                        Text(
                                            text = "Стоимость",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }


                                    Text(
                                        text = "От 600 рублей",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .padding(top = 6.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(18.dp)
                                )

                                Text(
                                    text = "Кол-во мест",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Text(
                                text = "11.08.2022 - 20.08.2022",
                                style = MaterialTheme.typography.titleSmall,

                                )

                        }

                        Box(
                            Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Surface(
                                tonalElevation = 16.dp,
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(
                                    text = "Новая", modifier = Modifier
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


