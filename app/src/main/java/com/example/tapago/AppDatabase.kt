package com.example.tapago

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tapago.data.daos.ExerciseDao
import com.example.tapago.data.daos.ExerciseSheetDao
import com.example.tapago.data.daos.SetExerciseSheetDao
import com.example.tapago.data.daos.SheetDao
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.data.entities.SetsExerciseSheetsEntity
import com.example.tapago.data.entities.SheetsEntity

@Database(
    entities = [
        ExercisesEntity::class,
        SheetsEntity::class,
        ExercisesSheetEntity::class,
        SetsExerciseSheetsEntity::class
    ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ExerciseDao(): ExerciseDao
    abstract fun SheetDao(): SheetDao
    abstract fun ExerciseSheetDao(): ExerciseSheetDao
    abstract fun SetExerciseSheetDao(): SetExerciseSheetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}