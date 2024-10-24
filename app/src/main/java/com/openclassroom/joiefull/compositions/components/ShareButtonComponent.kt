package com.openclassroom.joiefull.compositions.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.openclassroom.joiefull.R

/**
 * A composable function that displays a share button.
 * @param modifier The modifier to be applied to the button.
 * @param onShareClick The action to perform when the button is clicked.
 */
@Composable
fun ShareButton(modifier: Modifier, onShareClick: () -> Unit) {
    val shareButtonAccessibilityLabel = stringResource(R.string.share_button_accessibility_label)
    IconButton(
        onClick = onShareClick,
        modifier = modifier.semantics {
            onClick(label = shareButtonAccessibilityLabel, action = {true})
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = stringResource(R.string.share_button_content_description),
            tint = Color.Black
        )
    }
}


/**
 * A preview of the ShareButton composable.
 */
@Preview(showSystemUi = true)
@Composable
fun ShareButtonPreview() {
    ShareButton(modifier = Modifier,onShareClick ={})
}