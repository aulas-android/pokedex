package com.danigor.pokedex.data.network.model

sealed class Pokemon {
    var isFavorite: Boolean = false

    data class PokemonWithEvolution(val pokemon: List<PokemonResponse>) : Pokemon() {
        override fun equals(other: Any?): Boolean {
            if (other is PokemonWithEvolution) {
                return this.pokemon.toString() == other.pokemon.toString()
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return pokemon.toString().hashCode()
        }
    }
    data class PokemonWithoutEvolution(val pokemon: PokemonResponse) : Pokemon() {
        override fun equals(other: Any?): Boolean {
            if (other is PokemonWithoutEvolution) {
                return this.pokemon.id == other.pokemon.id
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return pokemon.id.hashCode()
        }
    }
}
