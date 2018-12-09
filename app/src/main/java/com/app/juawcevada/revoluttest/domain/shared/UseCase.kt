package com.app.juawcevada.revoluttest.domain.shared

import kotlinx.coroutines.CoroutineScope


abstract class UseCase<in P, R> {

    operator fun invoke(coroutineScope: CoroutineScope, parameters: P): R = execute(coroutineScope, parameters)

    protected abstract fun execute(coroutineScope: CoroutineScope, parameters: P): R
}
