package com.davidups.marvel.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.davidups.marvel.core.navigation.MainActivity
import com.davidups.marvel.exception.Failure
import kotlinx.android.synthetic.main.navigation_activity.progress

abstract class BaseFragment(layout: Int) : Fragment() {

    val layouID = layout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layouID, container, false)

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
            if (this is MainActivity) {
                this.progress.visibility = viewStatus
            }
        }

    internal fun showSpinner(show: Boolean) {
        when (show) {
            true -> showProgress()
            false -> hideProgress()
        }
    }

    internal fun navigateUp() {
        findNavController().navigateUp()
    }

    internal open fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    internal open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> toast("You seem to have no connection.")
            else -> toast("Error displaying information")
        }
    }

    fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
