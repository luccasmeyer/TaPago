package com.example.tapago.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("profile")
data class ProfileEntity(

    @PrimaryKey(autoGenerate = true) val profileId: Int = 0,
    @ColumnInfo("nameProfile") val nameProfile: String,
    @ColumnInfo("weight") val weightProfile: Double,
    @ColumnInfo("height") val heightProfile: Double
)
