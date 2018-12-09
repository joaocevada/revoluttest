package com.app.juawcevada.revoluttest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.juawcevada.revoluttest.databinding.CurrencyExchangeListFragmentBinding
import com.app.juawcevada.rickspace.extensions.setUpSnackbar
import com.app.juawcevada.rickspace.extensions.viewModelProvider
import com.app.juawcevada.rickspace.ui.shared.FragmentDataBindingComponent
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CurrencyExchangeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentDataBindingComponent: FragmentDataBindingComponent

    private lateinit var viewModel: CurrencyExchangeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: CurrencyExchangeListFragmentBinding =
            CurrencyExchangeListFragmentBinding.inflate(inflater, container, false).also {
                it.setLifecycleOwner(this)
                it.viewModel = viewModel
                it.list.apply {
                    adapter = CurrenciesAdapter(
                        fragmentDataBindingComponent,
                        viewModel::changeValue
                    ) { newCurrency ->
                        viewModel.changeCurrency(newCurrency)
                    }
                }

            }

        setUpSnackbar(viewModel.errorMessage, binding.root)

        return binding.root
    }

}
