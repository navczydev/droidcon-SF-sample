package com.navgde.droidconsf.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview()
@Composable
fun Abc() {


    var animate by remember { mutableStateOf(false) }
    val angle by animateFloatAsState(
        targetValue = if (animate) 360f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "arc"
    )

    Column {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text("I am button", color = Color.White, style = TextStyle(background = Color.White))
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)) {
                drawArc(
                    color = Color.Red,
                    startAngle = -90f,
                    sweepAngle = angle,
                    useCenter = true,
                )
            }
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = { animate = !animate }
            ) {
                Text(
                    text = "Toggle"
                )
            }
        }
    }
}