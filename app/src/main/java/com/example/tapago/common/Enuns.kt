package com.example.tapago.common

enum class Goal(val valueApi: String, val textShow: String) {
    MUSCLE_MASS("muscle_mass", "Ganho de massa"),
    WEIGHT_LOSS("weight_loss", "Perda de peso"),
    MAINTENANCE("maintenance", "Manutenção");
    companion object {
        fun fromString(valor: String): Goal? {
            return entries.find { it.valueApi == valor }
        }

        fun getTextGoal(valor: String): String {
            return fromString(valor)?.textShow ?: "Objetivo desconhecido"
        }
    }
}