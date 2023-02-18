package ru.android.stuttravel.feature.profile.presentation.componentetsUi

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PersonalInformationCard(
    fio: String,
    phone: String,
    email: String,
    birthday: String,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            Column {
                Text(
                    text = "ФИО",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .width(40.dp)
                       , contentAlignment = Alignment.CenterStart){
                        Text(
                            text = fio,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .width(170.dp),
                            maxLines = 2
                        )
                    }

                    Box(modifier = Modifier
                        .fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                        FilledTonalIconButton(onClick = { }, modifier = Modifier) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "df"
                            )
                        }
                    }
                }





                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "Номер телефона",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)

                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = phone,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )

                    FilledTonalIconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "df"
                        )
                    }

                }

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)

                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )

                    FilledTonalIconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "df"
                        )
                    }

                }

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "Дата рождения",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)

                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = birthday,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )

                    FilledTonalIconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "df"
                        )
                    }

                }

            }

        }

    }
}