package com.example.cardsstack.app.tickets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.cardsstack.app.components.TicketCard
import kotlin.random.Random

@Composable
fun TicketsScreen(
    modifier: Modifier = Modifier,
) {
    val overlapHeight = 180
    var selectedIndex by remember { mutableIntStateOf(0) }

    val colorsList = remember { generateColorsList() }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .animateContentSize() // Smoothly resizes when selected item moves
        ) {

            colorsList.forEachIndexed { index, color ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = if (index == 0) 0.dp else (-index * overlapHeight).dp) // Move up for stacking effect
                        .zIndex(if (index == selectedIndex) 1f else -index.toFloat()) // Ensure proper layering
                        .clip(RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    TicketCard(
                        modifier = Modifier,
                        isSelected = index == selectedIndex,
                        cardColor = color,
                        onClick = { selectedIndex = index },
                    )
                }
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
