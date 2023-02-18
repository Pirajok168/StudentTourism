package ru.android.stuttravel.feature.events.presentation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Room
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.android.stuttravel.feature.events.presentation.componentsUi.HeaderView
import ru.android.stuttravel.feature.events.presentation.componentsUi.RoomsViewer
import ru.android.stuttravel.feature.events.presentation.viewmodel.AboutEventViewModel
import ru.android.stuttravel.feature.events.presentation.viewmodel.EventAbout


sealed class Type(
    val label: String,
    val icon: ImageVector,
    val index: Int
){
    object Description: Type("Описание", Icons.Outlined.Description, 0)
    object Rooms: Type("Жильё поблизости", Icons.Outlined.Room, 1)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun AboutEvent(
    idEvent: String,
    idUni: String,
    aboutEventViewModel: AboutEventViewModel = viewModel(),
    onBack: () -> Unit,
    toDetailRoom: (idRoom: String) -> Unit
) {
    val state = aboutEventViewModel.uiState
    val tabs = listOf(Type.Description, Type.Rooms)
    var selectedTab: Type by remember {
        mutableStateOf(Type.Description)
    }

    LaunchedEffect(key1 = Unit, block = {
        aboutEventViewModel.event(EventAbout.LoadData(idUni, idEvent))
    })
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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
               .verticalScroll(rememberScrollState())
       ) {
           if (state.events != null)
               HeaderView(events = state.events)


           ScrollableTabRow(
               selectedTabIndex = selectedTab.index,
               tabs = {
                   tabs.forEachIndexed { index, item ->
                       val animate by animateDpAsState(
                           if (selectedTab.index == index) 12.dp else 1.dp,
                           animationSpec = tween(
                               easing = LinearOutSlowInEasing,
                           )
                       )
                       ElevatedCard(
                           modifier = Modifier
                               .padding(
                                   start = 16.dp, top = 16.dp, bottom = 24.dp,
                                   end = if (index == tabs.count() - 1) 12.dp else 1.dp
                               )

                               .height(50.dp),
                           onClick = {
                               selectedTab = item
                           },
                           elevation = CardDefaults.elevatedCardElevation(
                               defaultElevation = animate
                           ),

                           ) {
                           Box(
                               modifier = Modifier.fillMaxSize(),
                               contentAlignment = Alignment.Center
                           ) {
                               Row(
                                   modifier = Modifier
                                       .padding(8.dp),
                                   verticalAlignment = Alignment.CenterVertically,
                                   horizontalArrangement = Arrangement.spacedBy(8.dp)
                               ) {
                                   Icon(
                                       imageVector = item.icon,
                                       contentDescription = ""
                                   )

                                   Text(text = item.label)
                               }
                           }

                       }
                   }

               },
               divider = {},
               indicator = {},
               edgePadding = 0.dp,
           )

           when(selectedTab){
               Type.Description -> {
                   if (state.events != null){
                       Text(
                           text = state.events.description,
                           style = MaterialTheme.typography.bodyMedium,
                           textAlign = TextAlign.Start,
                           modifier = Modifier.padding(horizontal = 16.dp)
                       )
                   }
               }
               Type.Rooms -> {
                   RoomsViewer(
                       modifier = Modifier
                           .padding(horizontal = 16.dp),
                       state.relatedRoom,
                       toCheckInfoRoom = {
                           toDetailRoom(it)
                       }
                   )
               }
           }

       }
    }

}