package com.davidups.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidups.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure get() = _failure

    private val _showSpinner: MutableLiveData<Boolean> = MutableLiveData()
    val showSpinner get() = _showSpinner

    protected fun handleFailure(failure: Failure?) {
        _failure.value = failure
    }

    protected fun handleShowSpinner(show: Boolean) {
        _showSpinner.value = show
    }
}
