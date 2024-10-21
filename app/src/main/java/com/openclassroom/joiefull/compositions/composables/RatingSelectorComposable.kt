package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.compositions.components.RatingStarsBar

/**
 * A composable function that displays a rating selector and the user profile picture.
 *
 * @param ratingState The state of the rating.
 * @param modifier The modifier to be applied to the composable.
 * @param onRatingChanged The callback function to be called when the rating is changed.
 * @param userPicturePainter The painter to be used for the user picture.
 */
@Composable
fun RatingSelector(
    ratingState:MutableState<Float>,
    modifier: Modifier,
    onRatingChanged: (Float) -> Unit,
    userPicturePainter: Painter
){
    Row(modifier = modifier) {
        //User picture
        Image(
            painter =  userPicturePainter ,
            contentDescription = stringResource(id = R.string.user_picture_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(45.dp)
        )
        //Rating stars selector bar
        RatingStarsBar(
            modifier = Modifier
                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                .align(Alignment.CenterVertically),
            ratingState = ratingState,
            onRatingChanged = onRatingChanged)
    }
}

/**
 * A preview of the RatingSelector composable.
 */
@Preview(showSystemUi = true)
@Composable
fun RatingSelectorComposablePreview() {
    val ratingState = rememberSaveable{ mutableFloatStateOf(5.0f) }
    RatingSelector(ratingState,Modifier,onRatingChanged = {}, painterResource(id = R.drawable.demo_profile_pic))
}