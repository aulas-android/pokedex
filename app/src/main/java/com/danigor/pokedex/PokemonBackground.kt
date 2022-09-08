package com.danigor.pokedex

import java.util.*

object PokemonBackground {
    fun getPokemonColor(type: List<String?>): Int {
        val firstType = type[0]
        val color = when (firstType?.lowercase(Locale.ROOT)) {
            "grass", "bug" -> R.color.lightTeal
            "fire" -> R.color.lightRed
            "water", "fighting", "normal" -> R.color.lightBlue
            "electric", "psychic" -> R.color.lightYellow
            "poison", "ghost" -> R.color.lightPurple
            "ground", "rock" -> R.color.lightBrown
            "dark" -> R.color.black
            else -> R.color.lightBlue
        }
        return color
    }
}