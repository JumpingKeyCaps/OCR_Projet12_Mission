package com.openclassroom.joiefull.compositions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.compositions.composables.BodyCard
import com.openclassroom.joiefull.compositions.components.CommentaryField
import com.openclassroom.joiefull.compositions.composables.HeaderCard
import com.openclassroom.joiefull.compositions.components.DescriptionProduct
import com.openclassroom.joiefull.compositions.composables.RatingSelector
import com.openclassroom.joiefull.viewmodel.ProductDetailsViewModel

/**
 * The composable function of the screen composition to display the product details.
 *
 * @param productId The id of the product to display.
 * @param onBackClick The function to call when the back button is clicked.
 * @param isExpandedMode The flag to indicate if the screen is in expanded mode.
 */
@Composable
fun DetailsScreen(
    productId: Int,
    onBackClick: () -> Boolean,
    isExpandedMode: Boolean) {

    val viewModel = hiltViewModel<ProductDetailsViewModel,ProductDetailsViewModel.Factory>(
        key = productId.toString(),
        creationCallback = {
            it.create(productId)
        }
    )

    //--- the product from the viewmodel
    val product by viewModel.product.collectAsStateWithLifecycle()
    //--- the product details from the viewmodel
    val productDetails by viewModel.productDetails.collectAsStateWithLifecycle()


    //--- the text  and rating input values
    val commentaryTextInput = remember { mutableStateOf("") }
    val ratingState = remember{ mutableFloatStateOf(0f) }
    LaunchedEffect(productDetails){
        ratingState.floatValue = productDetails.rating
        commentaryTextInput.value = productDetails.commentary
    }


    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(0.dp)
    ) { it->
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true)
        ) {
            //---Product header (picture, likes)
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 30.dp)
            ){
                val pictureAccessibilityLabelUpscale = stringResource(R.string.product_picture_accessibility_label_upscale)
                val pictureAccessibilityLabelDownscale = stringResource(R.string.product_picture_accessibility_label_downscale)

                val pictureScaleMode = remember{ mutableStateOf(false) }
                val dpi = LocalDensity.current.density
                HeaderCard(
                    modifier = Modifier.align(Alignment.TopCenter),
                    url = product?.picture?.url ?: "null",
                    likes = product?.likes ?: 0,
                    filterQuality = FilterQuality.High,
                    onLikeButtonClick = {
                        viewModel.updateLikeValue(!productDetails.isLiked)
                    },
                    isLiked = productDetails.isLiked,
                    likeSizing = 18,
                    pictureModifier = Modifier
                        .fillMaxWidth()
                        .height(if (pictureScaleMode.value) (currentWindowSize().height / (dpi*1.26f)).dp else (currentWindowSize().height / (dpi*1.85f)).dp)
                        .padding(0.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { pictureScaleMode.value = !pictureScaleMode.value }
                        .semantics {
                            onClick(label = if(pictureScaleMode.value)pictureAccessibilityLabelDownscale else pictureAccessibilityLabelUpscale , action = {true})
                        },
                    likeModifier = Modifier
                        .width(70.dp)
                        .height(33.dp)
                        .padding(1.dp)
                        .offset(x = (-20).dp, y = (-20).dp),
                    onBackClick = onBackClick,
                    isDetailsMode = true,
                    isExpandedMode = isExpandedMode,
                    onShareClick = {
                        //on share button click action, share the product name and price, commentary, and deep link
                        //todo ---- SHARE FEATURE ----
                    },
                    pictureProductContentDescription = product?.picture?.description ?: " Photo du ${product?.name}"
                )
            }
            //---Product body (name, price, original price, ratings)
            BodyCard(
                title = product?.name ?: "",
                rating = productDetails.rating,
                price = product?.price ?: 0f,
                originalPrice = product?.originalPrice ?: 0f,
                sizing = 18,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 2.dp, 20.dp, 0.dp)
            )
            //---Product description
            val emptyProductDefaultDescription = stringResource(R.string.empty_product_description, product?.name ?: "")
            DescriptionProduct(
                description = product?.description ?: emptyProductDefaultDescription,
                modifier = Modifier.padding(20.dp,10.dp,20.dp,0.dp)
            )
            //---Rating Selector
            RatingSelector(
                modifier = Modifier.padding(20.dp,20.dp,20.dp,0.dp),
                ratingState = ratingState,
                onRatingChanged = {
                    ratingState.floatValue = it
                    viewModel.addProductDetails(productDetails.copy(rating = it))
                },
                userPicturePainter = painterResource(id = R.drawable.demo_profile_pic) // not specified in the project !
                //Painter resource to replace by the user_picture logic, and call the picture url via coil like this:
                // rememberAsyncImagePainter(url, contentScale = ContentScale.Crop, filterQuality = FilterQuality.High,placeholder = painterResource(R.drawable.userPicture_placeholder))
            )
            //---Commentary Field
            CommentaryField(
                value = commentaryTextInput.value,
                onValueChange = {
                    commentaryTextInput.value = it
                    viewModel.addProductDetails(productDetails.copy(commentary = it))},
                keyboardActions = { viewModel.addProductDetails(productDetails.copy(commentary = it))} ,
                modifier = Modifier.padding(20.dp,20.dp,20.dp,20.dp)
            )
        }
    }

}

/**
 * Preview of the product details screen.
 */
@Preview(showSystemUi = true)
@Composable
fun ProductDetailsScreenPreviews() {
    DetailsScreen(0,{true},false)
}