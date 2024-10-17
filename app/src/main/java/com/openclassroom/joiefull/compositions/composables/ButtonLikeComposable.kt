package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * A composable function that displays a button to add a product to favorites.
 * @param onClick The action to perform when the button is clicked.
 */
@Composable
fun LikeButton(likes: Int,modifier: Modifier,onClick: () -> Unit,isLiked: Boolean, sizing: Int) {

    //#######################

    val likedIcon =  if (isLiked) Icons.Filled.Favorite else Icons.Default.FavoriteBorder


    //###########################

    ElevatedButton(modifier = modifier,
        onClick = {
            onClick() // Call the action from the onClick parameter
        },
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.White),
        contentPadding = PaddingValues(1.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(modifier = Modifier.size(sizing.dp),
                imageVector = likedIcon, //change the liked icon to match his state
                contentDescription = "Like",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = likes.toString(), fontSize = sizing.sp, color = Color.Black, style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LikeButtonPreview() {
    LikeButton(40,Modifier,onClick = {},isLiked = false,sizing = 14)
}