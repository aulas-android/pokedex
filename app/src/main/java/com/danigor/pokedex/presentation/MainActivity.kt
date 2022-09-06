package com.danigor.pokedex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.api.PokedexDataSource
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val pokedexDataSource = PokedexDataSource(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvPokedex = findViewById<RecyclerView>(R.id.rvPokedex)


        lifecycleScope.launch {
            val pokedex = pokedexDataSource.getPokedex()
            val secondPokedex = pokedexDataSource.getPokedexFromCache()

            with(rvPokedex) {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = PokemonAdapter(pokedex)
            }
        }
    }
}