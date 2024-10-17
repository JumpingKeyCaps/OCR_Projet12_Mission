package com.openclassroom.joiefull.compositions.composables

import android.view.MotionEvent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.viewmodel.ProductDetailsViewModel

@Composable
fun RatingBar2(
    modifier: Modifier = Modifier,
    size: Dp = 34.dp,
    ratingIconPainter: Painter = painterResource(id = R.drawable.rounded_kid_star_24),
    selectedColor: Color = Color(0xFFFFD700),
    unselectedColor: Color = Color(0xFFA2ADB1),
    ratingState: MutableState<Float>,
    onRatingChanged: (Float) -> Unit
) {

    Row(modifier = modifier.wrapContentSize()) {
        // Star Icon Generation Loop ( rating on 5 stars)
        for (value in 1..5) {
            StarIcon(
                size = size,
                painter = ratingIconPainter,
                ratingValue = value, //stars value
                ratingState = ratingState, // rating of the user
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onRatingChanged = {onRatingChanged(it)}
            )
        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StarIcon(
    size: Dp,
    ratingState: MutableState<Float>,
    painter: Painter,
    ratingValue: Int,
    selectedColor: Color,
    unselectedColor: Color,
    onRatingChanged: (Float) -> Unit
) {
    val tint by animateColorAsState(
        targetValue = if (ratingValue <= ratingState.value) selectedColor else unselectedColor,
        label = ""
    )
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .padding(10.dp,0.dp,0.dp,0.dp)
            .size(size)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        onRatingChanged(ratingValue.toFloat())
                    }
                }
                true
            },
        tint = tint
    )
}


@Preview(showSystemUi = true)
@Composable
fun RatingBar2Preview() {
    val ratingState = remember { mutableFloatStateOf(0.0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      //  RatingBar2(ratingState = ratingState)
    }
}



