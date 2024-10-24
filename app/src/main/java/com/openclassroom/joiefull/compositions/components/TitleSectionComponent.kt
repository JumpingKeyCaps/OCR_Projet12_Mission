package com.openclassroom.joiefull.compositions.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.ui.theme.JoiefullTheme
import java.util.Locale

/**
 * A composable function that displays a title section with a given title.
 *
 * @param title The title to be displayed in the section.
 * @param modifier The modifier to be applied to the composable.
 */
@Composable
fun TitleSection(title: String, modifier: Modifier = Modifier) {
    val titleCategoryContentDescription = stringResource(R.string.title_category_content_description,title)
    Text(text = (title.lowercase(Locale.ROOT)).replaceFirstChar { it.uppercase() },
        fontSize = 22.sp,
        color = Color.Black,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.W500,
        modifier = modifier
            .padding(5.dp,5.dp,5.dp,5.dp)
            .fillMaxWidth()
            .semantics {
                contentDescription = titleCategoryContentDescription
                heading()
            }
    )
}


/**
 * A preview of the TitleSection composable.
 */
@Preview(showBackground = true)
@Composable
fun TitleSectionPreview() {
    JoiefullTheme {
        TitleSection("Hauts")
    }
}
