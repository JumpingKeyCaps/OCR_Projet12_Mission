package com.openclassroom.joiefull.compositions.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.R

/**
 * A composable function that displays a text field for entering a comment on a product.
 *
 * @param value The current value of the text field.
 * @param keyboardActions The callback function that is called when the user presses the done button on the keyboard.
 * @param onValueChange The callback function that is called when the value of the text field changes.
 * @param modifier The modifier to be applied to the text field.
 */
@Composable
fun CommentaryField(
    value: String,
    keyboardActions: (String)->Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier) {
    val focusManager = LocalFocusManager.current   //Manager of the keyboard focus
    OutlinedTextField(
        value = value,
        onValueChange = {onValueChange(it)},
        label = {
             Text(
                 text = (if(value.isEmpty()) stringResource(R.string.commentary_placeholder)  else stringResource(R.string.commentary_placeholder_short)),
                )
                },
        shape = RoundedCornerShape(10.dp),
        minLines = 2,
        textStyle = TextStyle(fontSize = 14.sp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus() //Clear the focus on the text field when the user presses the done button on the keyboard.
                keyboardActions(value)
            }),
        modifier = modifier
            .fillMaxWidth(),
        )
}


/**
 * A preview of the CommentaryField composable.
 */
@Preview
@Composable
fun CommentaryFieldPreview() {
    CommentaryField(value = "", onValueChange = {}, keyboardActions = {},modifier = Modifier)
}