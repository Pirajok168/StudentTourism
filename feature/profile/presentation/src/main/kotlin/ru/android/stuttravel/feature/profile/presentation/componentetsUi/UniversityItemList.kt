package ru.android.stuttravel.feature.profile.presentation.componentetsUi

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.android.stuttravel.core.theme.StudentTravelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.UniversityItemList(univ: String) {
    var isEditUniverstity by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(
        visible = !isEditUniverstity,
        exit = fadeOut() + slideOutHorizontally()
    ) {
        Column {
            Text(
                text = "ВУЗ",
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
                    text = univ,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )

                FilledTonalIconButton(onClick = {
                    isEditUniverstity = true
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "df"
                    )
                }

            }
        }
    }
    var text by rememberSaveable { mutableStateOf("") }
    AnimatedVisibility(
        visible = isEditUniverstity,
        enter = fadeIn()
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .height(300.dp),
            query = text,
            onQueryChange = { text = it } ,
            onSearch = {

            } ,
            active = isEditUniverstity,
            onActiveChange = {
                if (!it){
                    isEditUniverstity = false
                }
                Log.d("test", it.toString())
            },
            tonalElevation = 0.dp,
            trailingIcon = {
                IconButton(onClick = {
                    isEditUniverstity = false
                }) {
                    Icon(Icons.Default.Done, contentDescription = null)
                }

            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(4) { idx ->
                    val resultText = "Suggestion $idx"
                    @OptIn(ExperimentalMaterial3Api::class)
                    ListItem(
                        headlineText = { Text(resultText) },
                        supportingText = { Text("Additional info") },
                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                        modifier = Modifier.clickable {
                            text = resultText
                            //closeSearchBar()
                        }
                    )
                    if (idx != 3) { Divider() }
                }
            }
        }
    }
}


