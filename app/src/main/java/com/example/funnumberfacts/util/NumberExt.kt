package com.example.funnumberfacts.util

fun Int?.orInvalidId() = this ?: -1

fun Long?.orInvalidId() = this ?: -1