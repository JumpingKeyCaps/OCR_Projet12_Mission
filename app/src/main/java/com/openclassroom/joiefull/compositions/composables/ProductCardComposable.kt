package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails

/**
 * Composable function for a product element card section
 *
 * @param product The product to display
 * @param productDetails The product details to display
 * @param modifier The modifier to apply to the composable
 * @param pictureModifier The modifier to apply to the picture
 * @param onLikeButtonClick The callback to invoke when the like button is clicked
 */
@Composable
fun ProductCard(
    product: Product,
    productDetails: ProductDetails,
    modifier: Modifier = Modifier,
    pictureModifier: Modifier,
    onLikeButtonClick: (ProductDetails) -> Unit = {}
    ) {
    Card(modifier = modifier.padding(0.dp)) {
        Column(modifier = Modifier.background(Color.White),verticalArrangement = Arrangement.spacedBy(0.dp)) {

            //1 - Header of the product card (Product picture + like button)
            HeaderCard(
                modifier = Modifier
                    .width(198.dp)
                    .height(198.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                url = product.picture.url,
                likes = product.likes,
                filterQuality = FilterQuality.Low,
                onLikeButtonClick = {
                    onLikeButtonClick(productDetails)
                },
                isLiked = productDetails.isLiked,
                likeSizing = 14,
                pictureModifier = pictureModifier
                    .width(198.dp)
                    .height(198.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(20.dp)),
                likeModifier = Modifier
                    .width(51.dp)
                    .height(27.dp)
                    .padding(1.dp)
                    .offset(x = (-10).dp, y = (-10).dp),
                onBackClick = {},
                isDetailsMode = false,
                isExpandedMode = false
            )

            //2 - Body of the product card (Title + price + original price + rating)
            BodyCard(
                title = product.name,
                rating = productDetails.rating,
                price = product.price,
                originalPrice = product.originalPrice,
                sizing = 14,
                modifier = Modifier
                    .widthIn(198.dp, 198.dp)
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
            )
        }
    }
}


/**
 * Preview of the ProductCard composable
 */
@Preview(showSystemUi = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        product = Product(id = 1, name = "Veste urbaine", rating = 4.3f, price = 89f, originalPrice = 120f, likes = 12, picture = Product.Picture("url","description"),category = "Vestes"),
        productDetails = ProductDetails(id = 1, isLiked = false),
        pictureModifier = Modifier.clickable {}
    )
}