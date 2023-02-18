package ru.android.stuttravel.feature.profile.presentation.componentetsUi

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.android.stuttravel.core.theme.StudentTravelTheme

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SpecializationItemList(specialization: String) {



    Text(
        text = "Специализация",
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
            text = specialization,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
        )

        FilledTonalIconButton(onClick = {

        }) {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                contentDescription = "df"
            )
        }

    }



}


