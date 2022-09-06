package com.danigor.pokedex.data.network.api

import com.danigor.pokedex.data.network.model.PokemonResponse
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokedex.json")
    suspend fun getPokedex(): List<PokemonResponse>
}