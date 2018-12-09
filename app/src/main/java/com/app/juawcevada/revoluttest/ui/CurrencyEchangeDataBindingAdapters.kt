package com.app.juawcevada.revoluttest.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setCurrencyExchangeList")
fun setCurrencyExchangeList(recyclerView: RecyclerView, oldList: List<ExchangeItem>?, list: List<ExchangeItem>?) {
    (recyclerView.adapter as CurrenciesAdapter).submitList(list)

    // Little hack to make the list scroll the base is added (This shouldn't even be here)
    if (oldList != null && list != null && oldList.first() != list.first()) {
        recyclerView.scrollToPosition(0)
    }
}