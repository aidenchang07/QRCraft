package com.aiden.qrcraft.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by AidenChang on 2025/12/10
 */
@Composable
fun QRScanFrame(
    color: Color,
    strokeWidth: Dp,
    cornerRadius: Dp,
    borderSize: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .drawBehind {
                val cornerSizePx = cornerRadius.toPx()
                val sideOffset = cornerSizePx + borderSize.toPx()

                val sidePath = Path().apply {
                    addRect(
                        rect = Rect(
                            offset = Offset(sideOffset, 0f - sideOffset),
                            size = Size(
                                width = size.width - sideOffset * 2,
                                height = size.height + sideOffset * 2
                            )
                        )
                    )

                    addRect(
                        rect = Rect(
                            offset = Offset(0f - sideOffset, sideOffset),
                            size = Size(
                                width = size.width + sideOffset * 2,
                                height = size.height - sideOffset * 2
                            )
                        )
                    )
                }

                clipPath(
                    path = sidePath,
                    clipOp = ClipOp.Difference
                ) {
                    drawRoundRect(
                        brush = SolidColor(color),
                        style = Stroke(
                            width = strokeWidth.toPx()
                        ),
                        cornerRadius = CornerRadius(cornerSizePx, cornerSizePx)
                    )
                }
            }
            .clip(RoundedCornerShape(cornerRadius))
    )
}

@Preview
@Composable
private fun ScanCornerFramePreview() {
    // 原理是透過畫十字方框矩形，再畫最外框的矩形做裁切交集達成的
    QRScanFrame(
        modifier = Modifier
            .size(250.dp)
            .padding(8.dp),
        color = Color.Red,
        strokeWidth = 4.dp,
        cornerRadius = 24.dp,
        borderSize = 8.dp
    )
}

@Composable
private fun RectDemo(
    sideOffset: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {

        // 用 Path 畫出矩形
        val path = Path().apply {
            addRect(
                rect = Rect(
                    offset = Offset(sideOffset, 0f - sideOffset),
                    size = Size(
                        width = size.width - sideOffset * 2,
                        height = size.height + sideOffset * 2
                    )
                )
            )
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 4.dp.toPx()) // 邊框寬度
        )
    }
}

@Preview
@Composable
private fun RectDemoPreview() {
    Box(
        modifier = Modifier
            .size(250.dp),
        contentAlignment = Alignment.Center
    ) {
        RectDemo(
            sideOffset = 40f,
            color = Color.Red,
            modifier = Modifier.size(180.dp)
        )
    }
}