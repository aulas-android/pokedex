package com.danigor.pokedex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.api.PokedexDataSource
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val pokedexDataSource = PokedexDataSource(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val pokedex = pokedexDataSource.getPokedex()
            val secondPokedex = pokedexDataSource.getPokedexFromCache()
            Log.d("Pokemon", pokedex[0].toString())
            Log.d("Pokemon", secondPokedex[1].toString())
        }
    }
}