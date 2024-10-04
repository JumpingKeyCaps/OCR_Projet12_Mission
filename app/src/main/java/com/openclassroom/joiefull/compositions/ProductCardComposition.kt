package com.openclassroom.joiefull.compositions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.compositions.Composables.LikeButton
import com.openclassroom.joiefull.compositions.Composables.ProductPicture
import com.openclassroom.joiefull.compositions.Composables.RatingProduct
import java.nio.file.WatchEvent

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
    oldPrice: String,
    likes: Int,
    rating: Float,
    imageUrl: String,
    modifier: Modifier = Modifier

) {
    Card(modifier = modifier.padding(0.dp)) {
        Column(modifier = Modifier.background(Color.White),verticalArrangement = Arrangement.spacedBy(0.dp)) {
            //---BOX : PRODUCT PICTURE + LIKES BUTTON -----------
            Box(modifier = Modifier
                .width(198.dp)
                .height(198.dp)
                .padding(1.dp)
                .align(Alignment.CenterHorizontally)
            ){
                //---PRODUCT PICTURE (loaded with Coil)
                ProductPicture(imageUrl)
                //---LIKE BUTTON
                LikeButton(likes,modifier = Modifier.align(Alignment.BottomEnd).offset(x = (-10).dp, y = (-10).dp), onClick = {})
            }


/**

            Box(modifier = Modifier
                .width(198.dp)
                .padding(20.dp,0.dp,20.dp,10.dp)
            ){
                Column(modifier = Modifier.background(Color.White).align(Alignment.CenterStart)) {
                    //---TITLE
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(0.dp)
                    )
                    //---PRICE
                    Text(
                        text = price,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyLarge,

                        )
                }
                Column(modifier = Modifier.background(Color.White).align(Alignment.CenterEnd)) {
                    //---RATING
                    RatingProduct(rating)
                    //---OLD PRICE
                    Text(
                        text = oldPrice,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.bodyLarge.merge(
                            TextStyle(textDecoration = TextDecoration.LineThrough)
                        ),
                        modifier = Modifier
                            .padding(1.dp)
                            .align(Alignment.End)
                    )
                }
            }
**/


            //colunm + row+row
            Column(modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp)) {
                Row(modifier = Modifier.widthIn(198.dp,198.dp).padding(10.dp,0.dp,10.dp,0.dp)) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start, modifier = Modifier.weight(50.0F)){
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            softWrap = true,
                            overflow = TextOverflow.Clip,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.End, modifier = Modifier.weight(20.0F)){
                        RatingProduct(rating,modifier = Modifier.padding(0.dp,0.dp,0.dp,0.dp))
                    }


                }
                Row(modifier = Modifier.widthIn(198.dp,198.dp).padding(10.dp,0.dp,10.dp,0.dp)) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start, modifier = Modifier.weight(25.0F)){
                        Text(
                            text = price,
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(0.dp).align(Alignment.Top)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End, modifier = Modifier.weight(25.0F)){
                        Text(
                            text = oldPrice,
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodyLarge.merge(
                                TextStyle(textDecoration = TextDecoration.LineThrough)
                            ),
                            modifier = Modifier.padding(0.dp).align(Alignment.Top)

                            )
                    }
                }

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
        oldPrice = "120$",
        likes = 40,
        rating = 4.3f,
        imageUrl = ""
    )
}