package ru.android.stuttravel.feature.home.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExpandedTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    maxHeight: Dp,
    pinnedHeight: Dp,
    isSearching: Boolean,
    toFiltersScreen: () -> Unit
) {
    val pinnedHeightPx: Float
    val maxHeightPx: Float
    LocalDensity.current.run {
        pinnedHeightPx = pinnedHeight.toPx()
        maxHeightPx = maxHeight.toPx()
    }

    SideEffect {
        if (scrollBehavior?.state?.heightOffsetLimit != pinnedHeightPx - maxHeightPx) {
            scrollBehavior?.state?.heightOffsetLimit = pinnedHeightPx - maxHeightPx
        }
        Log.e("scrollBehavior", (maxHeightPx - pinnedHeightPx + (scrollBehavior?.state?.heightOffset
            ?: 0f)).toString() + scrollBehavior.isPinned.toString())
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height((maxHeightPx - pinnedHeightPx + (scrollBehavior?.state?.heightOffset
                ?: 0f)).dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
            ,
            contentAlignment = if (isSearching) Alignment.TopStart else Alignment.Center
        ) {
            Column(
                verticalArrangement = if (isSearching) Arrangement.Top else Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(visible = !isSearching) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Найди своё",
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Thin,
                            modifier = Modifier.offset(y = (15).dp),
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = 26.sp
                        )
                        Text(
                            text = "Путешествие".uppercase(),
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp
                        )
                    }

                }

                var searchText by remember {
                    mutableStateOf("")
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .requiredHeight(65.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    AnimatedVisibility(visible = isSearching) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(bounded = true)

                                ) {
                                   // isSearching = false; focusManager.clearFocus()
                                }
                                .padding(end = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = ""
                            )
                        }

                    }
                    ElevatedCard(
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = 8.dp,
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 8.dp
                                ),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    "",
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .weight(1f)
                                )
                                BasicTextField(
                                    value = searchText,
                                    onValueChange = {
                                        searchText = it
                                    },
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    ),
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(7f)
                                        .onFocusChanged {
                                            Log.e("focus", it.toString())
                                            //isSearching = it.hasFocus
                                        },

                                    )
                                IconButton(
                                    onClick = toFiltersScreen,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.FilterAlt,
                                        "",


                                        )
                                }

                            }

                        }

                    }
                }


            }

        }
    }
}