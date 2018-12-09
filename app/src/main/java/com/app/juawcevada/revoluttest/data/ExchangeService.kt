package com.app.juawcevada.revoluttest.data

import com.app.juawcevada.revoluttest.model.ExchangeEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {
    @GET("latest")
    fun getCharacters(@Query("base") base: String): Deferred<ExchangeEntity>
}