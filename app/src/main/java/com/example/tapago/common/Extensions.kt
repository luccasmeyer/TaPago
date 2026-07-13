package com.example.tapago.common

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

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