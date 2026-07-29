package com.example.tapago.common

import androidx.room.TypeConverter
import com.example.tapago.domain.model.workout.Workout
import com.google.gson.Gson

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromWorkoutComplete(workout: Workout): String {
        return gson.toJson(workout)
    }

    @TypeConverter
    fun toWorkout(workoutString: String): Workout {
        return gson.fromJson(workoutString, Workout::class.java)
    }
}