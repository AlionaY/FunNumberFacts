package com.example.funnumberfacts.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import com.example.funnumberfacts.ui.theme.LightGray
import com.example.funnumberfacts.ui.theme.Melanzane
import com.example.funnumberfacts.ui.theme.OldLavender

@Composable
fun NumberFactItem(
    item: FactItem?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = (item?.number ?: 0).toString(),
            fontSize = 25.sp,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp),
            color = Melanzane
        )

        Text(
            text = item?.text.orEmpty(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 10.dp),
            color = Melanzane
        )
    }

    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = OldLavender,
        thickness = 1.dp
    )
}

@Preview
@Composable
private fun FactItemPreview() {
    val item = FactItem(id = 0, number = 30, text = "ldkjhfvgn;slhvn;osdnv;gsold")
    FunNumberFactsTheme {
        NumberFactItem(
            item = item,
            modifier = Modifier.background(LightGray),
            onClick = {})
    }
}