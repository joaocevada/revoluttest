package com.app.juawcevada.revoluttest.util.builder

import com.app.juawcevada.revoluttest.model.ExchangeEntity
import com.app.juawcevada.revoluttest.model.RatesEntity

@ExchangeEntityDsl
class ExchangeEntityBuilder {
    var base: String = "EUR"
    var date: String = "2018-09-06"
    var rates: RatesEntity = ratesEntity{}

    fun base(body: ()-> String) {
        base = body()
    }

    fun date(body: ()-> String) {
        date = body()
    }

    fun rates(body: RatesEntityBuilder.()-> Unit) {
        rates = RatesEntityBuilder().apply(body).build()
    }

    fun build() = ExchangeEntity(base, date, rates)
}

fun exchangeEntity(body: ExchangeEntityBuilder.() -> Unit) = ExchangeEntityBuilder().apply(body).build()