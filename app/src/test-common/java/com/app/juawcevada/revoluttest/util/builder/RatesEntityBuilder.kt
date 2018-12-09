package com.app.juawcevada.revoluttest.util.builder

import com.app.juawcevada.revoluttest.model.RatesEntity

@ExchangeEntityDsl
class RatesEntityBuilder {

    var AUD: Float? = 1.0f
    var BGN: Float? = 1.0f
    var BRL: Float? = 1.0f
    var CAD: Float? = 1.0f
    var CHF: Float? = 1.0f
    var CNY: Float? = 1.0f
    var CZK: Float? = 1.0f
    var DKK: Float? = 1.0f
    var GBP: Float? = 1.0f
    var HKD: Float? = 1.0f
    var HRK: Float? = 1.0f
    var HUF: Float? = 1.0f
    var IDR: Float? = 1.0f
    var ILS: Float? = 1.0f
    var INR: Float? = 1.0f
    var ISK: Float? = 1.0f
    var JPY: Float? = 1.0f
    var KRW: Float? = 1.0f
    var MXN: Float? = 1.0f
    var MYR: Float? = 1.0f
    var NOK: Float? = 1.0f
    var NZD: Float? = 1.0f
    var PHP: Float? = 1.0f
    var PLN: Float? = 1.0f
    var RON: Float? = 1.0f
    var RUB: Float? = 1.0f
    var SEK: Float? = 1.0f
    var SGD: Float? = 1.0f
    var THB: Float? = 1.0f
    var TRY: Float? = 1.0f
    var USD: Float? = 1.0f
    var ZAR: Float? = 1.0f
    var EUR: Float? = 1.0f

    fun AUD(body: () -> Float?){
        AUD = body()
    }
    fun BGN(body: () -> Float?){
        BGN = body()
    }
    fun BRL(body: () -> Float?){
        BRL = body()
    }
    fun CAD(body: () -> Float?){
        CAD = body()
    }
    fun CHF(body: () -> Float?){
        CHF = body()
    }
    fun CNY(body: () -> Float?){
        CNY = body()
    }
    fun CZK(body: () -> Float?){
        CZK = body()
    }
    fun DKK(body: () -> Float?){
        DKK = body()
    }
    fun GBP(body: () -> Float?){
        GBP = body()
    }
    fun HKD(body: () -> Float?){
        HKD = body()
    }
    fun HRK(body: () -> Float?){
        HRK = body()
    }
    fun HUF(body: () -> Float?){
        HUF = body()
    }
    fun IDR(body: () -> Float?){
        IDR = body()
    }
    fun ILS(body: () -> Float?){
        ILS = body()
    }
    fun INR(body: () -> Float?){
        INR = body()
    }
    fun ISK(body: () -> Float?){
        ISK = body()
    }
    fun JPY(body: () -> Float?){
        JPY = body()
    }
    fun KRW(body: () -> Float?){
        KRW = body()
    }
    fun MXN(body: () -> Float?){
        MXN = body()
    }
    fun MYR(body: () -> Float?){
        MYR = body()
    }
    fun NOK(body: () -> Float?){
        NOK = body()
    }
    fun NZD(body: () -> Float?){
        NZD = body()
    }
    fun PHP(body: () -> Float?){
        PHP = body()
    }
    fun PLN(body: () -> Float?){
        PLN = body()
    }
    fun RON(body: () -> Float?){
        RON = body()
    }
    fun RUB(body: () -> Float?){
        RUB = body()
    }
    fun SEK(body: () -> Float?){
        SEK = body()
    }
    fun SGD(body: () -> Float?){
        SGD = body()
    }
    fun THB(body: () -> Float?){
        THB = body()
    }
    fun TRY(body: () -> Float?){
        TRY = body()
    }
    fun USD(body: () -> Float?){
        USD = body()
    }
    fun ZAR(body: () -> Float?){
        ZAR = body()
    }
    fun EUR(body: () -> Float?){
        EUR = body()
    }

    fun build() = RatesEntity(
        AUD,
        BGN,
        BRL,
        CAD,
        CHF,
        CNY,
        CZK,
        DKK,
        GBP,
        HKD,
        HRK,
        HUF,
        IDR,
        ILS,
        INR,
        ISK,
        JPY,
        KRW,
        MXN,
        MYR,
        NOK,
        NZD,
        PHP,
        PLN,
        RON,
        RUB,
        SEK,
        SGD,
        THB,
        TRY,
        USD,
        ZAR,
        EUR)
}

fun ratesEntity(body: RatesEntityBuilder.() -> Unit) = RatesEntityBuilder().apply(body).build()