package com.app.juawcevada.revoluttest.ui.shared

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import com.app.juawcevada.revoluttest.testing.OpenClassOnDebug
import com.app.juawcevada.revoluttest.ui.shared.FragmentBindingAdapters
import javax.inject.Inject

/**
 * Component to set fragment binding adapters.
 */

@OpenClassOnDebug
class FragmentDataBindingComponent @Inject
constructor(private val fragment: Fragment) : DataBindingComponent {


    override fun getFragmentBindingAdapters(): FragmentBindingAdapters =
            FragmentBindingAdapters(fragment)

}