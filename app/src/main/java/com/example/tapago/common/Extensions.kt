package com.example.tapago.common

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.tapago.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun Fragment.navigateSafe(
    @IdRes idRes: Int,
    args: Bundle? = null,
    options: (NavOptions.Builder.() -> Unit)? = null,
    onNavigate: () -> Unit = {}
) {
    if (lifecycle.currentState >= Lifecycle.State.STARTED) {
        findNavController().navigate(
            resId = idRes, args = args, navOptions = options?.let {
                val builder = NavOptions.Builder()
                options.invoke(builder)
                builder.build()
            })
        onNavigate()
    }
}

fun Fragment.navigateSafe(
    directions: NavDirections,
    options: (NavOptions.Builder.() -> Unit)? = null,
    onNavigate: () -> Unit = {}
) {
    if (lifecycle.currentState >= Lifecycle.State.STARTED) {
        findNavController().navigate(
            directions = directions,
            navOptions = options?.let {
                val builder = NavOptions.Builder()
                options.invoke(builder)
                builder.build()
            },
        )
        onNavigate()
    }
}

fun Fragment.popBackStackSafe(@IdRes idRes: Int? = null, inclusive: Boolean = false) {
    if (lifecycle.currentState >= Lifecycle.State.STARTED) {
        idRes?.let {
            findNavController().popBackStack(it, inclusive)
        } ?: run {
            findNavController().popBackStack()
        }
    }
}

fun <T> Fragment.observe(
    flow: Flow<T>,
    estado: Lifecycle.State = Lifecycle.State.STARTED,
    acao: suspend CoroutineScope.(T) -> Unit
) {
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(estado) {
            flow.collect { valor ->
                this.acao(valor)
            }
        }
    }
}

fun Fragment.showRegisterDialog(
    onRegisterClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    val dialog = Dialog(requireContext())
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

    dialog.setContentView(R.layout.dialog_register_profile)
//    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    val btnYes = dialog.findViewById<MaterialButton>(R.id.yes_register_dialog)
    val btnNo = dialog.findViewById<MaterialButton>(R.id.not_register_dialog)

    btnYes.setOnClickListener {
        onRegisterClick()
        dialog.dismiss()
    }

    btnNo.setOnClickListener {
        onSkipClick()
        dialog.dismiss()
    }

    dialog.show()
}

fun Fragment.snackbar(
    message: String, click: Pair<String, () -> Unit>? = null, short: Boolean = true
): Snackbar? {
    if (view == null || message.isBlank()) return null
    val snackbar =
        Snackbar.make(view!!, message, if (short) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG)
    click?.let {
        snackbar.setAction(click.first) {
            click.second()
        }
    }
    snackbar.show()
    return snackbar
}