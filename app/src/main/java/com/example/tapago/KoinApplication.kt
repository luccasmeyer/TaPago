package com.example.tapago

import com.example.tapago.data.repository.ExerciseRepositoryImp
import com.example.tapago.data.repository.ProfileRepositoryImp
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.presentation.menu.MenuViewModel
import com.example.tapago.presentation.profile.ProfileViewModel
import com.example.tapago.presentation.profile.RegisterProfileViewModel
import com.example.tapago.presentation.workout.RegisterSheetViewModel
import com.example.tapago.presentation.workout.ListSheetsViewModel
import com.example.tapago.presentation.workout.exercise.RegisterExerciseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()) }
    single { ProfileRepositoryImp(get()) }
    single { ExerciseRepositoryImp(get()) }
    single { WorkoutRepositoryImp(get(), get(), get(), get()) }
    single { get<AppDatabase>().ProfileDao() }
    single { get<AppDatabase>().SheetDao() }
    single { get<AppDatabase>().ExerciseDao() }
    single { get<AppDatabase>().ExerciseSheetDao() }

    viewModel { MenuViewModel() }
    viewModel { ProfileViewModel(get()) }
    viewModel { RegisterProfileViewModel(get()) }
    viewModel { ListSheetsViewModel(get()) }
    viewModel { RegisterSheetViewModel(get(), get()) }
    viewModel { RegisterExerciseViewModel(get()) }
}