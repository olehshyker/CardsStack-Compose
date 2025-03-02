package com.example.cardsstack.app.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TicketCard(
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colorScheme.primaryContainer,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            scale.animateTo(
                targetValue = 1.1f,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.8f)
            .clickable(interactionSource, indication = null, onClick = onClick)
            .scale(scale.value) // Apply scale effect
            .graphicsLayer {
                shadowElevation = 4.dp.toPx()
                shape = TicketShape(12.dp.toPx(), 10.dp.toPx())
                clip = true
            }
            .background(cardColor)
    )
}

@Preview
@Composable
private fun PreviewMatchTicketCard() {

    MaterialTheme {
        TicketCard()
    }
}
