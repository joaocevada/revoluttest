package com.app.juawcevada.revoluttest.ui.shared

import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

class SnackbarMessage(@StringRes val messageId: Int,
                      val duration: Int = Snackbar.LENGTH_SHORT)