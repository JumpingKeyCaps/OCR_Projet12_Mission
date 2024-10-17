package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductDescription(description: String,modifier: Modifier){
    Text(text = description,
        fontSize = 14.sp,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        modifier = modifier
    )
}


@Preview(showSystemUi = true)
@Composable
fun ProductDescriptionPreview() {
    ProductDescription("Pull vert forêt à motif torsadé élégant, tricot finement travaillé avec manches bouffantes et col montant; doux et chaleureux",
        Modifier.padding(20.dp,0.dp,20.dp,0.dp))
}
