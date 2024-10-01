package com.openclassroom.joiefull.composition

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.openclassroom.joiefull.R

/**
 * Composition for a product element card section
 * @param title Product title
 * @param price Product price
 * @param imageUrl Product image URL
 */


@Composable
fun ProductCard(
    title: String,
    price: String,
    imageUrl: String,
    modifier: Modifier = Modifier
    // Add other properties as needed
) {
    Card(
        modifier = modifier.padding(20.dp)

    ) {
        Column {
            //product picture loaded with Coil
            Image(
                painter = rememberAsyncImagePainter(
                    imageUrl
                 //   placeholder = painterResource(R.drawable.productplaceholder)
                ),
                contentDescription = "Product image",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .align(Alignment.CenterHorizontally)

            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Price: $price",
                    style = MaterialTheme.typography.bodySmall
                )

            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        title = "Veste urbaine",
        price = "89$",
        imageUrl = "https://media.glamour.com/photos/607f2749febb5e66fe7c52cf/1:1/w_1200,h_1200,c_limit/terry%20cloth%20trend_jumpsuit.jpg"
    )
}