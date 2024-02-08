package com.example.funnumberfacts.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("text")
    val message: String,
    @SerialName("number")
    val number: String,
    @SerialName("type")
    val type: String
)