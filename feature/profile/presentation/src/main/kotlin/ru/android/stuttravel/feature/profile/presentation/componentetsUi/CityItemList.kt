package ru.android.stuttravel.feature.profile.presentation.componentetsUi

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.android.stuttravel.core.theme.StudentTravelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.CityItemList() {
    Text(
        text = "Город отправления",
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
            text = "Улан-Удэ",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
        )

        FilledTonalIconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.LocationCity,
                contentDescription = "df"
            )
        }

    }
}

@Preview
@Composable
private fun Preview () {
    StudentTravelTheme() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            AdditionalInformationCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(16.dp)

            )
        }

    }
}