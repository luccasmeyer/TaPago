package com.example.tapago.domain.common

enum class TypeExercise(val translatedName: String) {
    CHEST("Peito"),
    BACK("Costas"),
    ARM("Braço"),
    LEG("Perna"),
    SHOULDER("Ombro");

    companion object {
        fun getAllTranslatedNames(): List<String> {
            return entries.map { it.translatedName }
        }
    }
}