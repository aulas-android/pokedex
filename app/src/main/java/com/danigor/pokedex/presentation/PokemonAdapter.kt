package com.danigor.pokedex.presentation

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.model.PokemonInfo
import java.util.*

const val VIEW_TYPE_HEADER = 2
const val VIEW_TYPE_POKEMON = 1

class PokemonAdapter(
    private val pokemonList: MutableList<PokemonInfo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)
            PokemonViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is PokemonViewHolder -> holder.bindView(pokemonList[position] as PokemonInfo.Pokemon)
            is HeaderViewHolder -> holder.bindView(pokemonList[position] as PokemonInfo.Header)
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun getItemViewType(position: Int): Int {
        return when (pokemonList[position]) {
            is PokemonInfo.Pokemon -> VIEW_TYPE_POKEMON
            is PokemonInfo.Header -> VIEW_TYPE_HEADER
        }
    }
}

class PokemonViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val tvPokemonName: TextView by lazy {
        itemView.findViewById(R.id.tvPokemonName)
    }

    private val tvPokemonNumber: TextView by lazy {
        itemView.findViewById(R.id.tvPokemonNumber)
    }

    private val ivPokemon: ImageView by lazy {
        itemView.findViewById(R.id.ivPokemon)
    }

    private val tvPokemonFirstType: TextView by lazy {
        itemView.findViewById(R.id.tvPokemonFirstType)
    }

    private val tvPokemonSecondType: TextView by lazy {
        itemView.findViewById(R.id.tvPokemonSecondType)
    }

    fun bindView(item: PokemonInfo.Pokemon) {

        val pokemonResponse = item.pokemonResponse

        tvPokemonName.text = pokemonResponse.name
        tvPokemonNumber.text = pokemonResponse.id

        Glide.with(itemView.context).load(pokemonResponse.imageurl).into(ivPokemon)

        val color = getPokemonColor(pokemonResponse.typeofpokemon)
        itemView.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        val firstType = pokemonResponse.typeofpokemon.getOrNull(0)
        tvPokemonFirstType.apply {
            text = firstType
            isVisible = firstType != null
        }

        val secondType = pokemonResponse.typeofpokemon.getOrNull(1)
        tvPokemonSecondType.apply {
            text = secondType
            isVisible = secondType != null
        }
    }

    @ColorInt
    fun getPokemonColor(type: List<String?>): Int {
        val firstType = type[0]
        val color = when (firstType?.lowercase(Locale.ROOT)) {
            "grass", "bug" -> R.color.lightTeal
            "fire" -> R.color.lightRed
            "water", "fighting", "normal" -> R.color.lightBlue
            "electric", "psychic" -> R.color.lightYellow
            "poison", "ghost" -> R.color.lightPurple
            "ground", "rock" -> R.color.lightBrown
            "dark" -> R.color.black
            else -> R.color.lightBlue
        }
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(itemView.context, color)
    }

}

class HeaderViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val tvHeader: TextView by lazy {
        itemView.findViewById(R.id.tvHeader)
    }

    fun bindView(item: PokemonInfo.Header) {
        tvHeader.text = item.title
    }
}
