package com.danigor.pokedex.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.model.Pokemon

private const val VIEW_TYPE_EVOLUTION = 1
private const val VIEW_TYPE_NOT_EVOLUTION = 2

class PokemonAdapter(
    private val pokemonList: List<Pokemon>,
    private val selectedListener: OnPokemonSelectedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_EVOLUTION) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_evolution_pokemon, parent, false)

            EvolutionViewHolder(view, selectedListener)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)

            NonEvolutionViewHolder(view, selectedListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NonEvolutionViewHolder -> {
                val pokemon = (pokemonList[position] as Pokemon.PokemonWithoutEvolution)
                holder.bindView(pokemon)
            }
            is EvolutionViewHolder -> {
                val pokemon = (pokemonList[position] as Pokemon.PokemonWithEvolution)
                holder.bindView(pokemon)
            }
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun getItemViewType(position: Int): Int {
        return when (pokemonList[position]) {
            is Pokemon.PokemonWithEvolution -> VIEW_TYPE_EVOLUTION
            is Pokemon.PokemonWithoutEvolution -> VIEW_TYPE_NOT_EVOLUTION
        }
    }

    fun favoritePokemon(pokemon: Pokemon) {
        val pokemonIndex = pokemonList.indexOfFirst { pokemon == it }
        if (pokemonIndex != -1) {
            pokemonList[pokemonIndex].isFavorite = !pokemonList[pokemonIndex].isFavorite
            notifyItemChanged(pokemonIndex)
        }
    }
}