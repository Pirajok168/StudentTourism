package ru.android.stuttravel.feature.booking.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.android.stuttravel.core.theme.StudentTravelTheme
import ru.android.stuttravel.feature.booking.presentation.viewmodel.BookingViewModel
import ru.android.stuttravel.feature.booking.presentation.viewmodel.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    idDormitories: String,
    bookingViewModel: BookingViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
    val uisState = bookingViewModel.uiState

    LaunchedEffect(key1 = 0, block = {
        if (uisState.dormitories==null){
            bookingViewModel.event(Event.LoadData(idDormitories))
        }
    })

    var expanded by remember { mutableStateOf(false) }
    var showingDatePicker by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Общежитие")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            ) {


            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()



            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    readOnly = true,
                    value = uisState.selectedTypeRoom,
                    onValueChange = {},
                    label = { Text("Тип размещения") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = Color.Transparent),

                    )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    uisState.typeRooms.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                bookingViewModel.event(Event.SelectTypeRoom(selectionOption))
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ){
                OutlinedTextField(
                    modifier = Modifier,
                    value = "2",
                    onValueChange = {},
                    label = { Text("Количество мест") },
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilledTonalIconButton(onClick = {
                    showingDatePicker = true
                }) {
                    Icon(imageVector = Icons.Outlined.EditCalendar, contentDescription ="" )
                }
                
                Text(
                    text = "16.12.2022 - 15.04.2023",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            var isStudent by remember { mutableStateOf(true) }
            Divider(
                Modifier
                    .padding(top = 16.dp)
            )
            Column(  modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                Text(
                    text = "Автор заявки",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 16.sp
                )
            }

            Row(
               modifier = Modifier
                   .padding(horizontal = 16.dp)
                   .padding(top = 16.dp)
                   .selectableGroup()
                   .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isStudent,
                        onClick = { isStudent = true },
                    )
                    Text(text = "Студент")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    RadioButton(
                        selected = !isStudent,
                        onClick = { isStudent = false },
                    )
                    Text(text = "Образовательная организация")
                }
            }

            if (isStudent){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = uisState.fio,
                        onValueChange = {},
                        label = { Text("ФИО") },
                    )

                    Divider(
                        Modifier
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "Контакты для обратной связи",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 16.sp
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        value = uisState.number,
                        onValueChange = {},
                        label = { Text("Контактный телефон") },
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = uisState.email,
                        onValueChange = {},
                        label = { Text("Электронная почта") },
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        value = "",
                        onValueChange = {},
                        label = { Text("Дополнительная информация") },
                    )
                }

            }

        }
    }
}


