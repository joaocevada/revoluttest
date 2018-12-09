package com.app.juawcevada.revoluttest.ui.shared

import androidx.lifecycle.LiveData
import com.app.juawcevada.revoluttest.shared.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ReceiveChannelLiveDataWrapper<T>(
        private val coroutineScope: CoroutineScope,
        private val createDataChannel: () -> ReceiveChannel<T>,
        private val appDispatchers: AppDispatchers
): LiveData<T>() {

    var currentDataChannel: ReceiveChannel<T>? = null

    private val actor = coroutineScope.actor<Boolean>(
            context = appDispatchers.IO,
            capacity = Channel.CONFLATED) {
        for (msg in channel) {
            currentDataChannel = if (msg) {
                currentDataChannel?.cancel()
                createDataChannel().apply {
                    try {
                        while (!isClosedForReceive && hasActiveObservers()) {
                            postValue(receive())
                        }
                    } catch (e : ClosedReceiveChannelException) {

                    }
                }
            } else {
                currentDataChannel?.cancel()
                null
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        coroutineScope.launch(appDispatchers.Main) {
            actor.send(false)
        }
    }

    override fun onActive() {
        super.onActive()
        coroutineScope.launch(appDispatchers.Main) {
            actor.send(true)
        }
    }

}