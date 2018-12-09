package com.app.juawcevada.rickspace.ui.shared

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.app.juawcevada.revoluttest.testing.OpenClassOnDebug
import com.app.juawcevada.revoluttest.ui.shared.GlideApp

/**
 * Fragment view binders.
 */

@OpenClassOnDebug
class FragmentBindingAdapters(private val fragment: Fragment) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, oldUrl: String?, url: String?) {
        if (url == oldUrl) return
        GlideApp.with(fragment).load(url).circleCrop().into(imageView)
    }
}
