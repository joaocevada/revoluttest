package com.app.juawcevada.revoluttest.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.app.juawcevada.revoluttest.R
import com.app.juawcevada.revoluttest.databinding.CurrencyExchangeItemBinding
import com.app.juawcevada.revoluttest.ui.shared.ListRecyclerAdapter
import timber.log.Timber

class CurrenciesAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val onChangedValue: (value: Float) -> Unit,
    private val onChangedBase: (newCurrency: ExchangeItem) -> Unit
) :
    ListRecyclerAdapter<ExchangeItem, CurrencyExchangeItemBinding>(ItemDiffCallback()) {

    override fun bind(
        binding: CurrencyExchangeItemBinding,
        item: ExchangeItem,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Timber.d("EQX: itemName: ${item.currencyCode} payload: ${payloads.size} position: $position binding ${binding}")
        // If it was just an update, just update the value
        if (!payloads.isNotEmpty()) {
            binding.exchangeItem = item
        }

        binding.currencyValue.apply {
            removeTextChangedListener(onValueTextChanged)
            setText(item.value.toString(), TextView.BufferType.NORMAL)
            if (position == 0) {
                isEnabled = true
                addTextChangedListener(onValueTextChanged)
            } else {
                isEnabled = false
            }
        }
        binding.root.tag = item
    }

    override fun createBinding(parent: ViewGroup): CurrencyExchangeItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate<CurrencyExchangeItemBinding>(
            layoutInflater,
            R.layout.currency_exchange_item,
            parent,
            false,
            dataBindingComponent
        ).apply {
            root.setOnClickListener {
                onChangedBase(it.tag as ExchangeItem)
            }
        }
    }

    private val onValueTextChanged = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val value = s.toString().toFloatOrNull()
            value?.let { getItem(0).value = it }
            value?.let(onChangedValue)
        }

    }

    class ItemDiffCallback : DiffUtil.ItemCallback<ExchangeItem>() {
        override fun areItemsTheSame(oldItem: ExchangeItem, newItem: ExchangeItem) =
            newItem.currencyCode == oldItem.currencyCode


        override fun areContentsTheSame(oldItem: ExchangeItem, newItem: ExchangeItem) = newItem == oldItem

       override fun getChangePayload(oldItem: ExchangeItem, newItem: ExchangeItem): Any? {
            return newItem.value
        }
    }
}