package com.davidups.marvel.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidups.marvel.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure get() = _failure

    var showSpinner: MutableLiveData<Boolean> = MutableLiveData()

    protected fun handleFailure(failure: Failure?) {
        this.failure.value = failure
    }

    protected fun handleShowSpinner(show: Boolean) {
        this.showSpinner.value = show
    }
}
