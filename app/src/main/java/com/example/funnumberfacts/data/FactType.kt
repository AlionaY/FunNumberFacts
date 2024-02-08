package com.example.funnumberfacts.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class FactType {
    @SerialName("trivia")
    Trivia,
    @SerialName("math")
    Math,
    @SerialName("date")
    Date
}