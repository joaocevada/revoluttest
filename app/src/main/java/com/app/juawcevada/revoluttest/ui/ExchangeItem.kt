package com.app.juawcevada.revoluttest.ui
data class ExchangeItem(
    val currencyCode: String,
    val currencyName: String,
    val imageUrl: String,
    val rate: Float,
    var value: Float = 0.0f)