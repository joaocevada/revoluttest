package com.app.juawcevada.revoluttest.ui
import com.app.juawcevada.revoluttest.ui.shared.LceViewState

data class CurrencyExchangeViewState(
        override val isLoading: Boolean = true,
        val currencyList: List<ExchangeItem>? = null,
        override val errorMessage: String? = null
) : LceViewState