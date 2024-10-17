package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommentaryField(value: String, onValueChange: (String) -> Unit,modifier: Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Partagez ici vos impressions sur cette pièce") },
        shape = RoundedCornerShape(10.dp),
        minLines = 2,
        textStyle = TextStyle(fontSize = 14.sp),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun CommentaryFieldPreview() {
    CommentaryField(value = "", onValueChange = {},modifier = Modifier)
}