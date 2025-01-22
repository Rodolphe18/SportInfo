package com.francotte.android.sportinfo.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomePageChip(
    modifier: Modifier = Modifier,
    chipName: String,
    isSelected: Boolean,
    onSelectedCategoryChanged: (Boolean) -> Unit,
) {
    Surface(
        modifier = modifier
            .height(32.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {  }
            .toggleable(
                value = isSelected,
                onValueChange = {
                    if (isSelected) onSelectedCategoryChanged(true) else onSelectedCategoryChanged(
                        it
                    )
                }),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(Dp.Hairline, Color.Gray),
        color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.surface)
    {
        Text(
            text = chipName,
            color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = modifier.padding(horizontal = 8.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}