package com.app.juawcevada.revoluttest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Try
import com.app.juawcevada.revoluttest.R
import com.app.juawcevada.revoluttest.domain.GetExchangeDataUseCase
import com.app.juawcevada.revoluttest.model.ExchangeEntity
import com.app.juawcevada.revoluttest.shared.AppDispatchers
import com.app.juawcevada.revoluttest.util.builder.exchangeEntity
import com.app.juawcevada.revoluttest.util.observeTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrencyExchangeViewModelTest {
    private val appTestDispatchers =
        AppDispatchers(
            Dispatchers.Unconfined,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun loadDataLoading() = runBlocking {
        val testEntity = exchangeEntity {
            base { "EUR" }
            rates { EUR { null } }
        }
        val producer = produce {
            send(Try.just(testEntity))
        }

        val useCase: GetExchangeDataUseCase = mock {
            on { invoke(any(), any()) } doReturn producer
        }

        val viewModel = CurrencyExchangeViewModel(
            useCase,
            ExchangeEntityToExchangeListMapper(),
            appTestDispatchers
        ).apply {
            viewState.observeTest()
            errorMessage.observeTest()
        }

        with(viewModel.viewState.value!!) {
            assertNull(currencyList)
            assertEquals(true, isLoading)
            assertNull(errorMessage)
        }

        assertNull(viewModel.errorMessage.value)
    }

    @Test
    fun loadDataSuccess() = runBlocking {
        val testEntity = exchangeEntity {
            base { "EUR" }
            rates { EUR { null } }
        }
        val producer = produce(Dispatchers.Unconfined) {
            send(Try.just(testEntity))
        }

        val useCase: GetExchangeDataUseCase = mock {
            on { invoke(any(), any()) } doReturn producer
        }

        val viewModel = CurrencyExchangeViewModel(
            useCase,
            ExchangeEntityToExchangeListMapper(),
            appTestDispatchers
        ).apply {
            viewState.observeTest()
            errorMessage.observeTest()
        }

        with(viewModel.viewState.value!!) {
            assertEquals(33, currencyList!!.size)
            assertEquals(false, isLoading)
            assertNull(errorMessage)
        }

        assertNull(viewModel.errorMessage.value)
    }

    @Test
    fun loadDataError() = runBlocking {

        val producer = produce(Dispatchers.Unconfined) {
            send(Try.raise<ExchangeEntity>(Exception()))
        }

        val useCase: GetExchangeDataUseCase = mock {
            on { invoke(any(), any()) } doReturn producer
        }

        val viewModel = CurrencyExchangeViewModel(
            useCase,
            ExchangeEntityToExchangeListMapper(),
            appTestDispatchers
        ).apply {
            viewState.observeTest()
            errorMessage.observeTest()
        }

        with(viewModel.viewState.value!!) {
            assertNull(currencyList)
            assertEquals(false, isLoading)
            assertNotNull(errorMessage)
        }

        with(viewModel.errorMessage.value!!) {
            assertEquals(R.string.connection_error, getContentIfNotHandled()!!.messageId)
        }
    }
}