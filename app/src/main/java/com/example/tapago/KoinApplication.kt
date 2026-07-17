package com.example.tapago

import com.example.tapago.data.repository.ExerciseRepositoryImp
import com.example.tapago.data.repository.ProfileRepositoryImp
import com.example.tapago.data.repository.SheetRepositoryImp
import com.example.tapago.presentation.menu.MenuViewModel
import com.example.tapago.presentation.profile.ProfileViewModel
import com.example.tapago.presentation.profile.RegisterProfileViewModel
import com.example.tapago.presentation.workout.RegisterSheetViewModel
import com.example.tapago.presentation.workout.WorkoutViewModel
import com.example.tapago.presentation.workout.exercise.RegisterExerciseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()) }
    single { ProfileRepositoryImp(get()) }
    single { ExerciseRepositoryImp(get()) }
    single { SheetRepositoryImp(get()) }
    single { get<AppDatabase>().ProfileDao() }
    single { get<AppDatabase>().SheetDao() }
    single { get<AppDatabase>().ExerciseDao() }

    viewModel { MenuViewModel() }
    viewModel { ProfileViewModel(get()) }
    viewModel { RegisterProfileViewModel(get()) }
    viewModel { WorkoutViewModel(get()) }
    viewModel { RegisterSheetViewModel(get()) }
    viewModel { RegisterExerciseViewModel(get()) }
}