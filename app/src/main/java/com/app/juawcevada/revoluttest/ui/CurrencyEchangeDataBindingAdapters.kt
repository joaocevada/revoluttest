package com.app.juawcevada.revoluttest.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setCurrencyExchangeList")
fun setCurrencyExchangeList(recyclerView: RecyclerView, list: List<ExchangeItem>?) {
    (recyclerView.adapter as CurrenciesAdapter).submitList(list)
}