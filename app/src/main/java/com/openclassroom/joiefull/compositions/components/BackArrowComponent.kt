package com.openclassroom.joiefull.compositions.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.openclassroom.joiefull.R

/**
 * A composable function that displays a back arrow clickable icon.
 *
 * @param modifier The modifier to be applied to the icon.
 * @param onBackClick The callback function that is called when the user clicks the back arrow.
 */
@Composable
fun BackArrow(modifier: Modifier, onBackClick: () -> Unit) {
    IconButton(
        onClick = onBackClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow_content_description),
            tint = Color.Black
        )
    }
}


/**
 * A preview of the BackArrow composable.
 */
@Preview(showSystemUi = true)
@Composable
fun BackArrowPreview() {
    BackArrow(modifier = Modifier,onBackClick ={})
}