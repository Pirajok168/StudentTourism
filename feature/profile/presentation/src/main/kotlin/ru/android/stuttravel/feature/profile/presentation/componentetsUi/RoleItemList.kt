package ru.android.stuttravel.feature.profile.presentation.componentetsUi


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.android.stuttravel.core.theme.StudentTravelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.RoleItemList() {
    val options = listOf("Студент", "Молодой специалист")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    var isEditRole by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = !isEditRole,
    ) {
        Column() {
            Text(
                text = "Роль",
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
                    text = "Студент",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )

                FilledTonalIconButton(onClick = {

                    isEditRole = true
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "df"
                    )
                }

            }
        }
    }

    AnimatedVisibility(
        visible = isEditRole,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .weight(4.8f)
                    .wrapContentWidth(Alignment.Start)


            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor(),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Роль") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = Color.Transparent),

                    )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            FilledTonalIconButton(
                modifier = Modifier
                    .requiredSize(40.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.End),
                onClick = {
                    isEditRole = false
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Done,
                    contentDescription = "df"
                )
            }

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