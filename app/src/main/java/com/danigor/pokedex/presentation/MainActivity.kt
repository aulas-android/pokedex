package com.danigor.pokedex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.api.PokedexDataSource
import com.danigor.pokedex.data.network.model.Pokemon
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnPokemonSelectedListener {

    private val pokedexDataSource = PokedexDataSource(this)
    lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvPokedex = findViewById<RecyclerView>(R.id.rvPokedex)


        lifecycleScope.launch {
            //val pokedex = pokedexDataSource.getPokedex()
            val secondPokedex = pokedexDataSource.getPokedexFromCache()
            val thirdPokedex = pokedexDataSource.getEvolutionsPokedex()

            pokemonAdapter = PokemonAdapter(thirdPokedex.toList(), this@MainActivity)

            with(rvPokedex) {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = pokemonAdapter
            }
        }

    }

    override fun selectPokemon(pokemon: Pokemon) {
        pokemonAdapter.favoritePokemon(pokemon)
    }
}

interface OnPokemonSelectedListener {
    fun selectPokemon(pokemon: Pokemon)
}