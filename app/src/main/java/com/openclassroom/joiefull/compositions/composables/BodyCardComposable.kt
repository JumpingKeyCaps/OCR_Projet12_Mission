package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.compositions.components.RatingProduct

/**
 * A composable function that displays the product card body (Title, rating, price, old price).
 *
 * @param title The title of the product.
 * @param rating The rating of the product.
 * @param price The price of the product.
 * @param originalPrice The original price of the product.
 * @param sizing The size of the text.
 * @param modifier The modifier for the composable.
 */
@Composable
fun BodyCard(
    title: String,
    rating: Float,
    price: Float,
    originalPrice: Float,
    sizing: Int,
    modifier: Modifier){
    Column{
        //Top section:  Product name and product rating --
        Row(modifier = modifier) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start, modifier = Modifier.weight(50.0F)){
                Text(
                    text = title,
                    fontSize = sizing.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(0.dp).fillMaxWidth()
                )
            }
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End, modifier = Modifier.weight(20.0F)){
                RatingProduct(
                    note = rating,
                    sizing = sizing,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp,0.dp))
            }


        }
        //Bottom section:  product price and original price --
        Row(modifier = modifier) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start, modifier = Modifier.weight(50.0F)){
                Text(
                    text = "$price€",
                    fontSize = sizing.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(0.dp).align(Alignment.Top)
                )
            }
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End, modifier = Modifier.weight(50.0F)){
                Text(
                    text = "$originalPrice€",
                    fontSize = sizing.sp,
                    style = MaterialTheme.typography.bodyLarge.merge(
                        TextStyle(textDecoration = TextDecoration.LineThrough)
                    ),
                    modifier = Modifier.padding(0.dp).align(Alignment.Top)
                )
            }
        }
    }
}

/**
 * A preview of the BodyCard composable.
 */
@Preview(showSystemUi = true)
@Composable
fun BodyCardPreview() {
    BodyCard("Veste urbaine",4.3f,89f,120f,14,modifier = Modifier.widthIn(198.dp,198.dp))
}