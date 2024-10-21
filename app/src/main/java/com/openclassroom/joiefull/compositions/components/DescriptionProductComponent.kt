package com.openclassroom.joiefull.compositions.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A composable function that displays a product description.
 *
 * @param description The description of the product.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun DescriptionProduct(description: String, modifier: Modifier){
    Text(text = description,
        fontSize = 14.sp,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        modifier = modifier
    )
}

/**
 * A preview of the DescriptionProduct composable.
 */
@Preview(showSystemUi = true)
@Composable
fun DescriptionProductPreview() {
    DescriptionProduct("Pull vert forêt à motif torsadé élégant, tricot finement travaillé avec manches bouffantes et col montant; doux et chaleureux",
        Modifier.padding(20.dp,0.dp,20.dp,0.dp))
}
