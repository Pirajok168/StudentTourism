package ru.android.stuttravel.feature.viewinghousing.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.shared.feature.seeInfoDormitories.data.model.PresentationServices

@Composable
fun ServicesViewer(
    modifier: Modifier = Modifier,
    services: List<PresentationServices>,
) {
    Box(
        modifier = modifier


    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            services.forEach {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Стоимость (1 человек):",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .alignByBaseline()
                        )

                        Text(
                            text = "${it.price} рублей",
                            fontWeight = FontWeight.Thin,
                            modifier = Modifier
                                .alignByBaseline()
                        )
                    }

                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }


        }




    }
}