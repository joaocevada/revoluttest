package com.app.juawcevada.revoluttest.data

import arrow.core.Try
import com.app.juawcevada.revoluttest.shared.AppDispatchers
import com.app.juawcevada.revoluttest.util.builder.exchangeEntity
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class ExchangeRepositoryTest {

    private val appTestDispatchers =
        AppDispatchers(
            Dispatchers.Unconfined,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined)

    @Test
    fun getDataSuccess() = runBlocking {
        val testEntity = exchangeEntity {
            base { "EUR" }
        }
        val apiService: ExchangeService = mock {
            on {getCharacters(eq("EUR"))} doReturn CompletableDeferred(testEntity)
        }
        val exchangeRepository = ExchangeRepository(apiService, appTestDispatchers)
        val result = exchangeRepository.getCurrencyDataAutoUpdate(this, "EUR", 1000)

        assertEquals(Try.just(testEntity), result.receive())

        result.cancel()
    }

    @Test
    fun getDataSuccessTwoElements() = runBlocking {
        val testEntity0 = exchangeEntity {
            base { "EUR" }
            rates { GBP{ 1.12f } }
        }
        val testEntity1 = exchangeEntity {
            base { "EUR" }
            rates { GBP{ 1.13f } }
        }

        val responseStack = mutableListOf(
            CompletableDeferred(testEntity0),
            CompletableDeferred(testEntity1))

        val apiService: ExchangeService = mock {
            on {getCharacters(eq("EUR"))} doReturnConsecutively responseStack
        }
        val exchangeRepository = ExchangeRepository(apiService, appTestDispatchers)
        val result = exchangeRepository.getCurrencyDataAutoUpdate(this, "EUR", 1000)

        assertEquals(Try.just(testEntity0), result.receive())
        assertEquals(Try.just(testEntity1), result.receive())

        result.cancel()
    }
}