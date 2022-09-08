package com.danigor.pokedex.presentation

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danigor.pokedex.PokemonBackground
import com.danigor.pokedex.PokemonBackground.getPokemonColor
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.model.Pokemon
import com.danigor.pokedex.data.network.model.PokemonResponse

class EvolutionViewHolder(
    itemView: View,
    private val selectedListener: OnPokemonSelectedListener
) : RecyclerView.ViewHolder(itemView) {

    private val tvFirstPokemonName: TextView by lazy {
        itemView.findViewById(R.id.tvFirstPokemonName)
    }

    private val tvFirstPokemonNumber: TextView by lazy {
        itemView.findViewById(R.id.tvFirstPokemonNumber)
    }

    private val ivFirstPokemon: ImageView by lazy {
        itemView.findViewById(R.id.ivFirstPokemon)
    }

    private val tvSecondPokemonName: TextView by lazy {
        itemView.findViewById(R.id.tvSecondPokemonName)
    }

    private val tvSecondPokemonNumber: TextView by lazy {
        itemView.findViewById(R.id.tvSecondPokemonNumber)
    }

    private val ivSecondPokemon: ImageView by lazy {
        itemView.findViewById(R.id.ivSecondPokemon)
    }

    private val tvThirdPokemonName: TextView by lazy {
        itemView.findViewById(R.id.tvThirdPokemonName)
    }

    private val tvThirdPokemonNumber: TextView by lazy {
        itemView.findViewById(R.id.tvThirdPokemonNumber)
    }

    private val ivThirdPokemon: ImageView by lazy {
        itemView.findViewById(R.id.ivThirdPokemon)
    }

    private val groupLastEvolution: Group by lazy {
        itemView.findViewById(R.id.groupLastEvolution)
    }

    private val ivFav: ImageView by lazy {
        itemView.findViewById(R.id.ivFav)
    }


    fun bindView(item: Pokemon.PokemonWithEvolution) {

        itemView.setOnClickListener {
            selectedListener.selectPokemon(item)
        }

        val pokemonList = item.pokemon

        ivFav.isVisible = item.isFavorite

        //FIRST
        val firstPokemon: PokemonResponse = pokemonList[0]

        val color =
            ContextCompat.getColor(itemView.context, PokemonBackground.getPokemonColor(firstPokemon.typeofpokemon))
        itemView.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        tvFirstPokemonName.text = firstPokemon.name
        tvFirstPokemonNumber.text = firstPokemon.id

        Glide.with(itemView.context).load(firstPokemon.imageurl).into(ivFirstPokemon)

        //SECOND
        val secondPokemon: PokemonResponse = pokemonList[1]

        tvSecondPokemonName.text = secondPokemon.name
        tvSecondPokemonNumber.text = secondPokemon.id

        Glide.with(itemView.context).load(secondPokemon.imageurl).into(ivSecondPokemon)

        //THIRD
        val hasThirdEvolution = pokemonList.size == 3

        groupLastEvolution.isVisible = hasThirdEvolution

        if (hasThirdEvolution) {
            val thirdPokemon: PokemonResponse = pokemonList[2]

            tvThirdPokemonName.text = thirdPokemon.name
            tvThirdPokemonNumber.text = thirdPokemon.id

            Glide.with(itemView.context).load(thirdPokemon.imageurl).into(ivThirdPokemon)
        }
    }
}