package com.example.funnumberfacts.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme

@Composable
fun NumberFactItem(item: FactItem?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = (item?.number ?: 0).toString(),
            fontSize = 25.sp,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp)
        )

        Text(
            text = item?.text.orEmpty(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

    Divider(
        modifier = Modifier.fillMaxWidth(), color = Color.Gray, thickness = 1.dp
    )
}

@Preview
@Composable
private fun FactItemPreview() {
    val item = FactItem(id = 0, number = 30, text = "ldkjhfvgn;slhvn;osdnv;gsold")
    FunNumberFactsTheme {
        NumberFactItem(item = item, modifier = Modifier.background(Color.LightGray))
    }
}