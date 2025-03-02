package com.example.cardsstack.app.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class TicketShape(private val cornerRadius: Float, private val cutoutRadius: Float) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawTicketPath(
                size = size,
                cornerRadius = cornerRadius,
                cutoutRadius = cutoutRadius
            )
        )
    }
}

fun drawTicketPath(size: Size, cornerRadius: Float, cutoutRadius: Float): Path {
    return Path().apply {
        reset()

        val width = size.width
        val height = size.height

        val mainPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(0f, 0f, width, height),
                    topRight = CornerRadius(cornerRadius),
                    topLeft = CornerRadius(cornerRadius),
                    bottomRight = CornerRadius(cornerRadius),
                    bottomLeft = CornerRadius(cornerRadius),
                )
            )
        }

        val leftCutout = Path().apply {
            addOval(
                Rect(
                    left = -cutoutRadius,
                    top = height / 2 - cutoutRadius,
                    right = cutoutRadius,
                    bottom = height / 2 + cutoutRadius
                )
            )
        }

        val rightCutout = Path().apply {
            addOval(
                Rect(
                    left = width - cutoutRadius,
                    top = height / 2 - cutoutRadius,
                    right = width + cutoutRadius,
                    bottom = height / 2 + cutoutRadius
                )
            )
        }

        op(mainPath, leftCutout, PathOperation.Difference)
        op(this, rightCutout, PathOperation.Difference)
        close()
    }
}