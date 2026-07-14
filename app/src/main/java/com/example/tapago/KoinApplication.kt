package com.example.tapago
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        AppDatabase.getDatabase(androidContext())
    }
    factory {}
}