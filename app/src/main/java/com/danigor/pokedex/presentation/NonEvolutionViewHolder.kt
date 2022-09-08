package com.danigor.pokedex.presentation

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danigor.pokedex.PokemonBackground
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.model.Pokemon
import com.danigor.pokedex.data.network.model.PokemonResponse

class NonEvolutionViewHolder(
    itemView: View,
    private val selectedListener: OnPokemonSelectedListener
) : RecyclerView.ViewHolder(itemView) {

    private val tvPokemonName: TextView by lazy {
        itemView.findViewById(R.id.tvPokemonName)
    }

    private val tvPokemonNumber: TextView by lazy {
        itemView.findViewById(R.id.tvPokemonNumber)
    }

    private val ivPokemonPicture: ImageView by lazy {
        itemView.findViewById(R.id.ivPokemon)
    }

    private val ivFav: ImageView by lazy {
        itemView.findViewById(R.id.ivFav)
    }

    fun bindView(item: Pokemon.PokemonWithoutEvolution) {
        val pokemonResponse = item.pokemon

        val color =
            ContextCompat.getColor(itemView.context, PokemonBackground.getPokemonColor(pokemonResponse.typeofpokemon))
        itemView.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        itemView.setOnClickListener {
            selectedListener.selectPokemon(item)
        }

        tvPokemonName.text = pokemonResponse.name
        tvPokemonNumber.text = pokemonResponse.id

        Glide.with(itemView.context).load(pokemonResponse.imageurl).into(ivPokemonPicture)

        ivFav.isVisible = item.isFavorite
    }
}