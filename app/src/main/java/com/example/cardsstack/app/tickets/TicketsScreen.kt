@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cardsstack.app.tickets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.cardsstack.app.R
import com.example.cardsstack.app.components.TicketCard
import kotlin.random.Random

@Composable
fun TicketsScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val overlapHeight = remember { screenWidth.div(1.8f).times(0.7f) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    val colorsList = remember { generateColorsList() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name)
                    )
                }
            )
        }
    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize()
                .padding(contentPadding), // Smooth transition when selected item changes
            verticalArrangement = Arrangement.spacedBy(-overlapHeight),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 16.dp),
        ) {
            itemsIndexed(colorsList, key = { index, item -> index }) { index, color ->
                TicketCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(if (index == selectedIndex) 1f else -index.toFloat()), // Ensure proper layering
                    isSelected = index == selectedIndex,
                    cardColor = color,
                    onClick = { selectedIndex = index },
                )
            }
        }
    }
}

fun generateColorsList(): List<Color> {
    val colorsList = mutableListOf<Color>()

    repeat(20) {
        colorsList.add(randomColor())
    }

    return colorsList
}

fun randomColor(alpha: Int = 255) = Color(
    Random.nextInt(256),
    Random.nextInt(256),
    Random.nextInt(256),
    alpha = alpha
)
