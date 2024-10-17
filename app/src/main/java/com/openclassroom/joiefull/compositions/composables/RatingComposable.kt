package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.ui.theme.YellowRating

/**
 * A composable function that displays the rating of a product.
 */
@Composable
fun RatingProduct(note: Float,sizing: Int, modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        Icon(modifier = Modifier.size(16.dp),
            imageVector = Icons.Rounded.Star,
            contentDescription = "Like",
            tint = YellowRating
        )
        Spacer(modifier = Modifier.width(1.dp))
        Text(text = note.toString(), fontSize = sizing.sp, color = Color.Black, style = MaterialTheme.typography.bodyLarge)
    }


}


@Preview(showSystemUi = true)
@Composable
fun RatingProductPreview() {
    RatingProduct(4.3f,14)
}