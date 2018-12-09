package com.app.juawcevada.revoluttest.domain

import arrow.core.Try
import com.app.juawcevada.revoluttest.data.ExchangeRepository
import com.app.juawcevada.revoluttest.domain.shared.UseCase
import com.app.juawcevada.revoluttest.model.ExchangeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class GetExchangeDataUseCase @Inject constructor(
    private val currencyExchangeRepository: ExchangeRepository
) :
    UseCase<GetExchangeDataUseCase.ExchangeParams, ReceiveChannel<Try<ExchangeEntity>>>() {
    override fun execute(
        coroutineScope: CoroutineScope,
        parameters: ExchangeParams
    ): ReceiveChannel<Try<ExchangeEntity>> {

        return currencyExchangeRepository
            .getCurrencyDataAutoUpdate(
                coroutineScope,
                parameters.base,
                parameters.updateIntervalMs
            )
    }

    data class ExchangeParams(val base: String, val updateIntervalMs: Long)
}