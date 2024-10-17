package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.openclassroom.joiefull.R


/**
 * A composable function that displays a product picture.
 * @param imageUrl The URL of the product image.
 */

@Composable
fun ProductPicture(imageUrl: String,filterQuality: FilterQuality = FilterQuality.Low,modifier: Modifier){

    //---PRODUCT PICTURE (loaded with Coil)
    Image(
        painter = rememberAsyncImagePainter(
            imageUrl,
            contentScale = ContentScale.Crop, // Optionally crop the image
            filterQuality = filterQuality, // reduce the quality of the image
            placeholder = painterResource(R.drawable.productplaceholder),
        ),
        contentDescription = "Product image",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


@Preview(showSystemUi = true)
@Composable
fun ProductPicturePreview() {
    ProductPicture("",modifier = Modifier
        .width(198.dp)
        .height(198.dp)
        .padding(0.dp)
        .clip(RoundedCornerShape(20.dp)))
}