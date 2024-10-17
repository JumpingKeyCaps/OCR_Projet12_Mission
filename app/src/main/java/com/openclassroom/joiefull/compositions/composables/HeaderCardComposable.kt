package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays the product picture header (Picture + Like button).
 * @param modifier The modifier for the composable.
 * @param url The URL of the product image.
 * @param likes The number of likes for the product.
 * @param filterQuality The filter quality for the product image.
 * @param onLikeButtonClick The action to perform when the like button is clicked.
 * @param isLiked Whether the product is liked or not.
 *
 */
@Composable
fun HeaderCard(modifier: Modifier,
               url: String,
               likes: Int,
               filterQuality: FilterQuality,
               onLikeButtonClick: () -> Unit = {},
               isLiked: Boolean,
               likeSizing: Int,
               likeModifier: Modifier,
               pictureModifier: Modifier){
    Box(modifier = modifier.padding(1.dp)
    ){
        //---PRODUCT PICTURE (loaded with Coil)
        ProductPicture(url, filterQuality,modifier = pictureModifier)
        //---LIKE BUTTON
        LikeButton(likes,modifier = likeModifier.align(Alignment.BottomEnd),
            onClick = onLikeButtonClick,
            isLiked = isLiked,
            sizing = likeSizing)
    }
}