package com.app.juawcevada.revoluttest.data

import arrow.core.Try
import com.app.juawcevada.revoluttest.model.ExchangeEntity
import com.app.juawcevada.rickspace.dispatchers.AppDispatchers
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ExchangeRepository @Inject constructor(
    private val currencyExchangeService: ExchangeService,
    private val appDispatchers: AppDispatchers
) {

    fun getCurrencyDataAutoUpdate(
        croutineScope: CoroutineScope,
        base: String,
        intervalMs: Long
    ): ReceiveChannel<Try<ExchangeEntity>> = croutineScope.produce {
        try {
            while (true) {
                withContext(appDispatchers.IO) {
                    val result = Try { currencyExchangeService.getCharacters(base).await() }
                    send(result)
                    delay(intervalMs)
                }
            }
        } catch (e: CancellationException) {
            // we expect it to be cancelled and just need to continue
        }
    }
}