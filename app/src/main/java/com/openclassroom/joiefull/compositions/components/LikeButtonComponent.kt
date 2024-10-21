package com.openclassroom.joiefull.compositions.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.R


/**
 * A composable function that displays a button to add a product to favorites.
 *
 * @param likes The number of likes of the product.
 * @param modifier The modifier to be applied to the button.
 * @param onClick The action to perform when the button is clicked.
 * @param isLiked A boolean value indicating whether the product is liked or not.
 * @param sizing The size of the text and icon in the button.
 */
@Composable
fun LikeButton(likes: Int,modifier: Modifier,onClick: () -> Unit,isLiked: Boolean, sizing: Int) {
    ElevatedButton(modifier = modifier,
        onClick = {
            onClick()
        },
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.White),
        contentPadding = PaddingValues(1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(modifier = Modifier.size(sizing.dp),
                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.like_button_content_description),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = likes.toString(), fontSize = sizing.sp, color = Color.Black, style = MaterialTheme.typography.bodySmall)
        }
    }
}

/**
 * A preview of the LikeButton composable.
 */
@Preview(showSystemUi = true)
@Composable
fun LikeButtonPreview() {
    LikeButton(40,Modifier,onClick = {},isLiked = false,sizing = 14)
}