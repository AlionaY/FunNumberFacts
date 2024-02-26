package com.example.funnumberfacts.network.response

import com.example.funnumberfacts.data.FactType
import com.example.funnumberfacts.data.NumberFact
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(
    @SerialName("text")
    val fact: String,
    @SerialName("number")
    val number: Long,
    @SerialName("found")
    val found: Boolean,
    @SerialName("type")
    val type: FactType
)

fun FactResponse.toNumberFact() =
    NumberFact(
        number = number,
        fact = fact
    )