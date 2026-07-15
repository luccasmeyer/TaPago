package com.example.tapago.presentation.profile

import com.example.tapago.domain.model.Profile

data class ProfileState(

    val isLoading: Boolean = true,
    val message: String? = null,
    val profile: Profile? = null
)
