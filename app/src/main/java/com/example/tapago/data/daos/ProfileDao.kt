package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tapago.data.entities.ProfileEntity

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    suspend fun getProfile(): ProfileEntity

    @Insert
    suspend fun insertProfile(profileEntity: ProfileEntity)
}