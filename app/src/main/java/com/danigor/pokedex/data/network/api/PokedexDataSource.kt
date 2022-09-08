package com.danigor.pokedex.data.network.api

import android.content.Context
import android.util.Log
import com.danigor.pokedex.data.network.model.Pokemon
import com.danigor.pokedex.data.network.model.PokemonResponse
import com.danigor.pokedex.data.readJSONFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PokedexDataSource(private val context: Context) {

    private val pokemonApi by lazy {
        ApiClientBuilder.createService(PokemonApi::class.java, API_URL)
    }

    fun getPokedexFromCache(): List<PokemonResponse> {

        val pokedexJson = readJSONFromAsset(context, "Pokedex.json") ?: return emptyList()

        val type = object : TypeToken<List<PokemonResponse>>() {}.type
        return Gson().fromJson(pokedexJson, type)
    }

    fun getEvolutionsPokedex(): MutableSet<Pokemon> {
        val pokemonList: MutableSet<Pokemon> = mutableSetOf()

        val currentPokedex = getPokedexFromCache()

        currentPokedex.forEach { pokemonResponse ->
            val evolutions = pokemonResponse.evolutions.toMutableList().apply {
                remove(pokemonResponse.id)
            }

            if (evolutions.size > 0) {
                val pokemonEvolutions = currentPokedex.filter { pokemonResponse.evolutions.contains(it.id) }
                pokemonList.add(Pokemon.PokemonWithEvolution(pokemonEvolutions))
            } else {
                pokemonList.add(Pokemon.PokemonWithoutEvolution(pokemonResponse))
            }
        }

        return pokemonList
    }

    suspend fun getPokedex(): List<PokemonResponse> {
        return pokemonApi.getPokedex()
    }

    companion object {
        private const val API_URL = "https://gist.githubusercontent.com/igorvilela28/cadd72ad7314961ca9305fea29982abc/raw/41db9e5893ffd48cd2c3da19d8ed4c2dcb5eb04d/"
    }
}
