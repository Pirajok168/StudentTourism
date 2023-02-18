package ru.android.stuttravel.feature.profile.presentation.componentetsUi

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.android.stuttravel.core.theme.StudentTravelTheme

enum class State {
    Collapsed,
    Expanded
}


@Composable
fun AdditionalInformationCard(modifier: Modifier = Modifier) {


    ElevatedCard(
        modifier = modifier,
    ) {
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            Column {
                //region Role
                RoleItemList()
                //endregion Role

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                CityItemList()

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                //Region Университет
                UniversityItemList()
                //Endegion Университет

                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))

                SpecializationItemList()
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
                    .padding(16.dp)

            )
        }

    }
}