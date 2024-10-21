package com.openclassroom.joiefull.compositions.components

import androidx.compose.foundation.Image
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
 *
 * @param imageUrl The URL of the product image.
 * @param filterQuality The filter quality for the product image.
 * @param modifier The modifier for the composable.
 */
@Composable
fun PictureProduct(imageUrl: String, filterQuality: FilterQuality = FilterQuality.Low, modifier: Modifier,contentDescription: String){
    Image(
        painter = rememberAsyncImagePainter(
            imageUrl,
            contentScale = ContentScale.Crop, // crop the image
            filterQuality = filterQuality, // set adapted filter quality for the image
            placeholder = painterResource(R.drawable.productpictureplaceholder),
        ),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

/**
 * A preview of the ProductPicture composable.
 */
@Preview(showSystemUi = true)
@Composable
fun ProductPicturePreview() {
    PictureProduct("",
        contentDescription = "",
        modifier = Modifier
        .width(198.dp)
        .height(198.dp)
        .padding(0.dp)
        .clip(RoundedCornerShape(20.dp)))
}