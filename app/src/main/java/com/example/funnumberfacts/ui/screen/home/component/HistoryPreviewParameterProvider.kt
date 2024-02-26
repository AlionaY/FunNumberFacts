package com.example.funnumberfacts.ui.screen.home.component

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.funnumberfacts.db.FactItem

class HistoryPreviewParameterProvider() : PreviewParameterProvider<List<FactItem>> {
    override val values: Sequence<List<FactItem>> = sequenceOf(
        List(40) {
            FactItem(
                id = 0,
                number = 2,
                text = "2 is the number of stars in a binary star system (a stellar system consisting of two stars orbiting around their center of mass). "
            )
        },
        emptyList(),
        List(3) {
            FactItem(
                id = 0,
                number = 3,
                text = "3 is the number of words or phrases in a Tripartite motto."
            )
        }
    )
}