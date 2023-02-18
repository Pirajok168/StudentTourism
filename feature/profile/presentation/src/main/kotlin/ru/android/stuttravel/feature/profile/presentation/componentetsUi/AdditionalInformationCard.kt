package ru.android.stuttravel.feature.profile.presentation.componentetsUi

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.android.stuttravel.core.theme.StudentTravelTheme

enum class State {
    Collapsed,
    Expanded
}


@Composable
fun AdditionalInformationCard(
    modifier: Modifier = Modifier,
    role: String,
    city: String,
    univ: String,
    specialization: String
) {


    ElevatedCard(
        modifier = modifier,
    ) {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            Column {
                //region Role
                RoleItemList(role)
                //endregion Role

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                CityItemList(city)

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                //Region Университет
                UniversityItemList(univ)
                //Endegion Университет

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                SpecializationItemList(specialization)
            }

        }

    }
}

@Preview
@Composable
private fun Preview() {
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
                    .padding(16.dp),
                role = "Студент",
                city = "Улан-Удэ",
                univ = "ВСГУТУ",
                specialization = "Программная инженерия"

            )
        }

    }
}