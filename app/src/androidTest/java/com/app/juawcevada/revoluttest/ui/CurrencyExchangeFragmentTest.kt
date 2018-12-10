package com.app.juawcevada.revoluttest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.juawcevada.revoluttest.R
import com.app.juawcevada.revoluttest.shared.Event
import com.app.juawcevada.revoluttest.testing.SingleFragmentActivity
import com.app.juawcevada.revoluttest.ui.shared.FragmentBindingAdapters
import com.app.juawcevada.revoluttest.ui.shared.FragmentDataBindingComponent
import com.app.juawcevada.revoluttest.ui.shared.SnackbarMessage
import com.app.juawcevada.revoluttest.util.RecyclerViewMatcher
import com.app.juawcevada.revoluttest.util.builder.exchangeEntity
import com.app.juawcevada.revoluttest.util.createTestFactory
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CurrencyExchangeFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CurrencyExchangeViewModel
    private lateinit var errorMessage: MutableLiveData<Event<SnackbarMessage>>
    private lateinit var viewState: MutableLiveData<CurrencyExchangeViewState>
    private lateinit var activityScenario: ActivityScenario<SingleFragmentActivity>
    private lateinit var fragment: CurrencyExchangeFragment

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(SingleFragmentActivity::class.java)
        viewState = MutableLiveData()
        errorMessage = MutableLiveData()
        fragment = CurrencyExchangeFragment()

        viewModel = mock {
            on { viewState } doReturn this@CurrencyExchangeFragmentTest.viewState
            on { errorMessage } doReturn this@CurrencyExchangeFragmentTest.errorMessage
        }

        fragment.viewModelFactory = viewModel.createTestFactory()

        fragment.fragmentDataBindingComponent = object : FragmentDataBindingComponent(fragment) {
            override fun getFragmentBindingAdapters() = mock<FragmentBindingAdapters>()
        }
    }

    @Test
    fun showExchangeData() {
        val testEntity = exchangeEntity {
            base { "EUR" }
            rates { EUR { null } }
        }
        val testList = ExchangeEntityToExchangeListMapper().mapFrom(testEntity).apply {
            first().value = 1.0f
        }
        viewState.value = CurrencyExchangeViewState(isLoading = false, currencyList = testList)
        startFragment()

        onView(RecyclerViewMatcher(R.id.list).atPosition(0)).apply {
            check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("EUR"))))
            check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Euro"))))
            check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("1.0"))))
        }
    }

    @Test
    fun showLoading() {
        viewState.value = CurrencyExchangeViewState()
        startFragment()

        onView(withId(R.id.spin_kit)).check(matches(isDisplayed()))
    }

    @Test
    fun showError() {
        viewState.value = CurrencyExchangeViewState(isLoading = false, errorMessage = "Error")
        startFragment()

        onView(withId(R.id.error_text_title)).check(
            matches(
                CoreMatchers.allOf(
                    ViewMatchers.withText("Error")
                )
            )
        )
    }


    private fun startFragment() {
        activityScenario.onActivity {
            it.replaceFragment(fragment)
        }
    }

}