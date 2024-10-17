package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.model.Product

/**
 * Composition for a product element card section
 */


@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    pictureModifier: Modifier,

    ) {
    Card(modifier = modifier.padding(0.dp)) {
        Column(modifier = Modifier.background(Color.White),verticalArrangement = Arrangement.spacedBy(0.dp)) {

            //1 - Header card (picture + like button)
            HeaderCard(modifier = Modifier.width(198.dp)
                .height(198.dp)
                .align(Alignment.CenterHorizontally)
                .padding(0.dp,0.dp,0.dp,10.dp),
                product.picture.url,
                product.likes,
                FilterQuality.Low,
                onLikeButtonClick = {},
                isLiked = false,
                likeSizing = 14,
                pictureModifier = pictureModifier
                    .width(198.dp)
                    .height(198.dp)
                    .padding(0.dp)
                    .clip(RoundedCornerShape(20.dp)),
                likeModifier =  Modifier
                    .width(51.dp)
                    .height(27.dp)
                    .padding(1.dp)
                    .offset(x = (-10).dp, y = (-10).dp)
            )

            //2 - Body card (title + price + old price + rating)
            BodyCard(product.name,
                product.rating?:0.0f,
                product.price,
                product.originalPrice,14,modifier = Modifier.widthIn(198.dp,198.dp).padding(10.dp,0.dp,10.dp,0.dp))




        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        product = Product(id = 1, name = "Veste urbaine", rating = 4.3f, price = 89f, originalPrice = 120f, likes = 12, picture = Product.Picture("url","description"),category = "Vestes"),
        pictureModifier = Modifier.clickable {
            //image click !
        }
    )
}