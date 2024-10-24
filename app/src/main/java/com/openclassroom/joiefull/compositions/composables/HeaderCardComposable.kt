package com.openclassroom.joiefull.compositions.composables

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.compositions.components.BackArrow
import com.openclassroom.joiefull.compositions.components.LikeButton
import com.openclassroom.joiefull.compositions.components.PictureProduct
import com.openclassroom.joiefull.compositions.components.ShareButton

/**
 * A composable function that displays the product card header (Picture + Like button).
 *
 * @param modifier The modifier for the composable.
 * @param url The URL of the product image.
 * @param likes The number of likes for the product.
 * @param filterQuality The filter quality for the product image.
 * @param onLikeButtonClick The action to perform when the like button is clicked.
 * @param isLiked Whether the product is liked or not by the user.
 * @param likeSizing The size of the like button.
 * @param likeModifier The modifier for the like button.
 * @param pictureModifier The modifier for the product picture.
 * @param onBackClick The action to perform when the back button is clicked.
 * @param onShareClick The action to perform when the share button is clicked.
 * @param isDetailsMode Whether the composable is included in a composition for the product details mode or not.
 * @param isExpandedMode Whether the composable is in expanded mode or not.
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
               pictureModifier: Modifier,
               backArrowModifier: Modifier,
               onBackClick: () -> Boolean,
               shareButtonModifier: Modifier,
               onShareClick: () -> Unit = {},
               isDetailsMode: Boolean = false,
               isExpandedMode: Boolean = false,
               pictureProductContentDescription: String){
    Box(modifier = modifier.padding(1.dp)
    ){
        //---Product picture (loaded with Coil)
        PictureProduct(
            imageUrl = url,
            filterQuality = filterQuality,
            contentDescription = pictureProductContentDescription,
            modifier = pictureModifier
        )
        //---Product like button
        LikeButton(
            likes = likes,
            modifier = likeModifier.align(Alignment.BottomEnd),
            onClick = onLikeButtonClick,
            isLiked = isLiked,
            sizing = likeSizing
        )
        //filter to show or not the back arrow and share button in specific scenarios (details mode/productCard mode)
        if(isDetailsMode){
            if(!isExpandedMode){
                //---Back arrow button
                BackArrow(
                    modifier = backArrowModifier
                        .align(Alignment.TopStart)
                        .offset(x = (10).dp, y = (10).dp),
                    onBackClick = onBackClick
                )
            }
            //---Share button
            ShareButton(
                modifier = shareButtonModifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = (10).dp),
                onShareClick = {
                        onShareClick()
                }
            )
        }
    }
}


/**
 * A preview of the HeaderCard composable.
 */
@Preview(showSystemUi = true)
@Composable
fun HeaderCardPreview() {
    HeaderCard(modifier = Modifier,
        url = "url",
        likes = 0,
        filterQuality = FilterQuality.High,
        onLikeButtonClick = {},
        isLiked = false,
        likeSizing = 18,
        likeModifier = Modifier
            .width(70.dp)
            .height(33.dp)
            .padding(1.dp)
            .offset(x = (-20).dp, y = (-20).dp),
        pictureModifier = Modifier
            .height(431.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(20.dp)),
        onBackClick = {true},
        isDetailsMode = true,
        pictureProductContentDescription = "product name",
        backArrowModifier = Modifier,
        shareButtonModifier = Modifier
    )


}