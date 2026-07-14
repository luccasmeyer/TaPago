package com.example.tapago.data.mapper

import com.example.tapago.data.entities.ProfileEntity
import com.example.tapago.domain.model.Profile

fun ProfileEntity.toDomain(): Profile{
    return Profile(
        name = this.nameProfile,
        weight = this.weightProfile,
        height = this.heightProfile
    )
}