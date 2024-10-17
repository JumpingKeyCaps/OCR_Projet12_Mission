package com.openclassroom.joiefull.compositions.screens

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.openclassroom.joiefull.compositions.composables.BodyCard
import com.openclassroom.joiefull.compositions.composables.CommentaryField
import com.openclassroom.joiefull.compositions.composables.HeaderCard
import com.openclassroom.joiefull.compositions.composables.ProductDescription
import com.openclassroom.joiefull.compositions.composables.RatingSelector
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun ProductDetailsScreen(product: MutableState<Product?>? = null,navController: NavController? = null,viewModel: ProductDetailsViewModel = hiltViewModel()) {


   // viewModel.getProductDetailsById(product?.value?.id?:0)





    val productDetails by viewModel.productDetails.collectAsStateWithLifecycle()


//--- LIKE
    val productIsLiked by viewModel.productIsLiked.collectAsStateWithLifecycle()


//--- RATING
    val productRating by viewModel.productRating.collectAsStateWithLifecycle()
    val ratingState = remember{ mutableFloatStateOf(productRating) }
    LaunchedEffect(productRating){
        ratingState.floatValue = productRating
    }
//---





    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(0.dp)) { it->
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true)) {
            //---Product header (picture, likes)
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp, 20.dp, 30.dp)){
                //---PRODUCT PICTURE (loaded with Coil)
                HeaderCard(modifier = Modifier
                    .align(Alignment.TopCenter),
                    product?.value?.picture?.url ?: "url",
                    product?.value?.likes ?: 0,
                    FilterQuality.High,
                    onLikeButtonClick = {
                        //on like button click action, update the productDetails object with the new liked value and add it to the database
                        viewModel.updateLikeValue(!productIsLiked)
                    },
                    isLiked = productIsLiked,
                    likeSizing = 18,
                    pictureModifier = Modifier
                        .height(431.dp)
                        .padding(0.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {},
                    likeModifier = Modifier
                        .width(70.dp)
                        .height(33.dp)
                        .padding(1.dp)
                        .offset(x = (-20).dp, y = (-20).dp)
                )
            }
            //---Product body (name, price, original price, ratings)
            BodyCard(product?.value?.name ?: "",
                productRating,
                product?.value?.price ?: 0f,
                product?.value?.originalPrice ?: 0f,
                18,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 2.dp, 20.dp, 0.dp))

            //---Product description
            ProductDescription(product?.value?.picture?.description ?: "description",Modifier.padding(20.dp,10.dp,20.dp,0.dp))
            //---Rating Selector
            RatingSelector(
                modifier = Modifier.padding(20.dp,20.dp,20.dp,0.dp),
                ratingState = ratingState,
                onRatingChanged = {
                    ratingState.floatValue = it

                    viewModel.addProductDetails(productDetails.copy(rating = it))
                }
            )
            //---Commentary Field
            CommentaryField(value = "", onValueChange = {}, modifier = Modifier.padding(20.dp,20.dp,20.dp,0.dp))

        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun ProductDetailsScreenPreviews() {
    ProductDetailsScreen(null)
}