package com.danigor.pokedex.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.api.PokedexDataSource
import com.danigor.pokedex.data.network.model.PokemonInfo

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvPokedex = findViewById<RecyclerView>(R.id.rvPokedex)

        val pokemonList = PokedexDataSource(this).getPokedexFromCache()

        val pokemonItems: MutableList<PokemonInfo> = mutableListOf()

        pokemonItems.add(PokemonInfo.Header("My Pokémon"))

        pokemonList.forEach {
            pokemonItems.add(PokemonInfo.Pokemon(it))
        }

        pokemonAdapter = PokemonAdapter(pokemonItems.toMutableList())

        with(rvPokedex) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pokemonAdapter
        }
    }

}