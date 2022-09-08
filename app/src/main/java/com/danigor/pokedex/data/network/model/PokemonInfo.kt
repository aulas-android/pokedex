package com.danigor.pokedex.data.network.model

sealed class PokemonInfo {
    data class Pokemon(val pokemonResponse: PokemonResponse): PokemonInfo()
    data class Header(val title: String): PokemonInfo()
}
