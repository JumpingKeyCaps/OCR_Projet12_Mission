package com.openclassroom.joiefull.compositions.components

import android.view.MotionEvent
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.ui.theme.RatingBarSelectedColor
import com.openclassroom.joiefull.ui.theme.RatingBarUnselectedColor

/**
 * A composable function that displays a rating bar with 5 stars.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param size The size of the stars.
 * @param ratingIconPainter The painter for the rating icon.
 * @param selectedColor The color of the selected star.
 * @param unselectedColor The color of the unselected star.
 * @param ratingState The state of the rating.
 * @param onRatingChanged The callback function when the rating is changed.
 */
@Composable
fun RatingStarsBar(
    modifier: Modifier = Modifier,
    size: Dp = 34.dp,
    ratingIconPainter: Painter = painterResource(id = R.drawable.rounded_kid_star_24),
    selectedColor: Color = RatingBarSelectedColor,
    unselectedColor: Color = RatingBarUnselectedColor,
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


/**
 * A composable function that displays a touchable star icon.
 *
 * @param size The size of the star icon.
 * @param painter The painter for the star icon.
 * @param ratingValue The value of the rating.
 * @param selectedColor The color of the selected star.
 * @param unselectedColor The color of the unselected star.
 * @param ratingState The state of the rating.
 * @param onRatingChanged The callback function when the rating is changed.
 */
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
        label = stringResource(id = R.string.star_icon_tint_animation_label)
    )
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
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



/**
 * A preview of the RatingStarsBar composable.
 */
@Preview(showSystemUi = true)
@Composable
fun RatingStarsBarPreview() {
    val ratingState = remember { mutableFloatStateOf(0.0f) }
    Row( modifier = Modifier.fillMaxSize()) {
        RatingStarsBar(modifier = Modifier
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
            .align(Alignment.CenterVertically),
            ratingState = ratingState,
            onRatingChanged = {})
    }
}



