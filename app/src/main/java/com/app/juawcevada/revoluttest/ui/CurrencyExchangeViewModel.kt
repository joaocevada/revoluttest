package com.app.juawcevada.revoluttest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import arrow.core.Try
import com.app.juawcevada.revoluttest.domain.GetExchangeDataUseCase
import com.app.juawcevada.revoluttest.model.ExchangeEntity
import com.app.juawcevada.revoluttest.ui.shared.ReceiveChannelLiveDataWrapper
import com.app.juawcevada.revoluttest.ui.shared.ScopedViewModel
import com.app.juawcevada.revoluttest.ui.shared.SnackbarMessage
import com.app.juawcevada.revoluttest.ui.shared.ViewStateLiveData
import com.app.juawcevada.rickspace.dispatchers.AppDispatchers
import com.app.juawcevada.rickspace.event.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CurrencyExchangeViewModel @Inject constructor(
    private val getExchangeDataUseCase: GetExchangeDataUseCase,
    private val exchangeListMapper: ExchangeEntityToExchangeListMapper,
    private val appDispatchers: AppDispatchers
) : ScopedViewModel(), CurrencyExchangeViewActions {

    companion object {
        private const val UPDATE_MS = 1000L
        private const val START_VALUE = 1.0f
    }


    private val _viewState = ViewStateLiveData(CurrencyExchangeViewState())
    val viewState: LiveData<CurrencyExchangeViewState>
        get() = _viewState

    private val _errorMessage = MediatorLiveData<Event<SnackbarMessage>>()
    val errorMessage: LiveData<Event<SnackbarMessage>>
        get() = _errorMessage

    private val baseCurrency = MutableLiveData<String>().apply {
        value = "EUR"
    }

    private val currentValue = MutableLiveData<Float>()

    private val exchangeData: LiveData<Try<ExchangeEntity>> = Transformations.switchMap(baseCurrency) {
        ReceiveChannelLiveDataWrapper(
            this,
            { getExchangeDataUseCase(this, GetExchangeDataUseCase.ExchangeParams(it, UPDATE_MS)) },
            appDispatchers
        )
    }

    init {
        _viewState.addNewStateSource(exchangeData) { currencyEntity ->
            val newList = currencyEntity
                .map { exchangeListMapper.mapFrom(it) }
                .map { it.applyValueFromList(currencyList) }

            newList.fold(
                { copy(isLoading = false, errorMessage = it.toString()) },
                { copy(isLoading = false, currencyList = it.sortByOldList(currencyList)) }
            )
        }
        _viewState.addNewStateSource(currentValue) { newValue ->
            copy(currencyList = currencyList?.applyValue(newValue))
        }
        _viewState.addNewStateSource(baseCurrency) { newCurrencyCode ->
            copy(currencyList = currencyList?.setCurrencyCodeFirst(newCurrencyCode))
        }
    }

    override fun changeCurrency(newCurrencyCode: String) {
        baseCurrency.postValue(newCurrencyCode)
    }

    override fun changeValue(newValue: Float) {
        currentValue.postValue(newValue)
    }

    private fun List<ExchangeItem>.applyValueFromList(oldList: List<ExchangeItem>?): List<ExchangeItem> {
        return map {
            it.copy(value = (oldList?.first()?.value ?: START_VALUE) * it.rate)
        }
    }

    private fun List<ExchangeItem>.applyValue(newValue: Float): List<ExchangeItem> {
        return map {
            it.copy(value =  newValue * it.rate)
        }
    }

    private fun List<ExchangeItem>.setCurrencyCodeFirst(currencyCode: String): List<ExchangeItem> {
        val newBaseCurrency = find { it.currencyCode == currencyCode }!!
        val filteredList = filter { it.currencyCode != currencyCode }
        return mutableListOf(newBaseCurrency).apply {
            addAll(filteredList)
        }
    }

    private fun List<ExchangeItem>.sortByOldList(oldList: List<ExchangeItem>?): List<ExchangeItem> {
        if (oldList == null) return this
        val newList = mutableListOf<ExchangeItem>()
        oldList.forEach { oldListItem ->
            find { oldListItem.currencyCode == it.currencyCode }?.also {
                newList.add(it)
            }
        }
        return newList
    }
}