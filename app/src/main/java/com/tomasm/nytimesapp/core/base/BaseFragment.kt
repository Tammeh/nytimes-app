package com.tomasm.nytimesapp.core.base

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.tomasm.core.exception.Failure
import com.tomasm.nytimesapp.core.navigation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private fun progressStatus(show: Boolean) =
        with(activity) {
            if (this is MainActivity) {
                this.binding.progress.isVisible = show
            }
        }


    internal open fun handleShowSpinner(show: Boolean?) {
        progressStatus(show ?: false)
    }

    internal open fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> toast("Parece que no tienes conexión")
            else -> toast("Ha ocurrido un error inseperado")
        }
    }


    internal fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}