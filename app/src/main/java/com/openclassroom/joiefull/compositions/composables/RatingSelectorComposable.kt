package com.openclassroom.joiefull.compositions.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.viewmodel.ProductDetailsViewModel

@Composable
fun RatingSelector(ratingState:MutableState<Float>, modifier: Modifier,onRatingChanged: (Float) -> Unit) {
    Row(modifier = modifier) {
        Image(painter = rememberAsyncImagePainter(
            "https://s3-alpha-sig.figma.com/img/ab2d/a5da/9c0bc3c97dc5ac6884d5645d7763d63c?Expires=1729468800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=bUt2NgeGS2lrKFP9Ugkj94erse0aiFadsSzZVotPsu2weiXgFSkupulc2lEKPLwehfnQeABUVHCqySdKYfWBaVHSX4AP2Kr0bKt--lWpDP0YFPXbW8OvZYN2~lwE8GCTeROcPe8F1sg6xXQ8GiYnDwNFX~OupTQ9XXfSVTvK383sIwwtkCxoL0fhOSNBJAhVHjLVySqjG4UBw78HqGbn1z0fi8BkxPmbtCp3IWejtZwm1sYzIdgm9Q68gCIzRn8Ak9FORUlxvoqWul8Hhu0wm~SCxLw~F3kkK0Pccj7SWyB1G6NyQCZcalj~g66ZYXdVOiZhozyeM3IsinrtvjPMsw__",
            contentScale = ContentScale.Crop, // Optionally crop the image
            filterQuality = FilterQuality.Low, // reduce the quality of the image
            placeholder = painterResource(R.drawable.demo_profile_pic),
        ),
            contentDescription = "User Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(45.dp)
        )

        RatingBar2(modifier = Modifier
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
            .align(Alignment.CenterVertically),
            ratingState = ratingState,
            onRatingChanged = onRatingChanged)
    }



}

@Preview(showSystemUi = true)
@Composable
fun RatingSelectorComposablePreview() {
    val ratingState = rememberSaveable{ mutableFloatStateOf(0.0f) }
   // RatingSelector(3f,Modifier,onRatingChanged = {})
}