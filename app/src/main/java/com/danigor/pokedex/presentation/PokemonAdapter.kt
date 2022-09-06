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
import com.danigor.pokedex.data.network.model.PokemonResponse
import java.util.*

class PokemonAdapter(
    private val pokemon: List<PokemonResponse>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(pokemon[position])
    }

    override fun getItemCount(): Int = pokemon.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvPokemonName: TextView by lazy {
            itemView.findViewById(R.id.tvPokemonName)
        }

        private val tvPokemonNumber: TextView by lazy {
            itemView.findViewById(R.id.tvPokemonNumber)
        }

        private val tvPokemonFirstType: TextView by lazy {
            itemView.findViewById(R.id.tvPokemonFirstType)
        }

        private val tvPokemonSecondType: TextView by lazy {
            itemView.findViewById(R.id.tvPokemonSecondType)
        }

        private val tvPokemonThirdType: TextView by lazy {
            itemView.findViewById(R.id.tvPokemonThirdType)
        }

        private val ivPokemonPicture: ImageView by lazy {
            itemView.findViewById(R.id.ivPokemon)
        }

        fun bindView(item: PokemonResponse) {


            tvPokemonName.text = item.name
            tvPokemonNumber.text = item.id

            val firstType = item.typeofpokemon.getOrNull(0)
            tvPokemonFirstType.apply {
                text = firstType
                isVisible = firstType != null
            }

            val color = getPokemonColor(firstType)
            itemView.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            val secondType = item.typeofpokemon.getOrNull(1)
            tvPokemonSecondType.apply {
                text = secondType
                isVisible = secondType != null
            }

            val thirdType = item.typeofpokemon.getOrNull(2)
            tvPokemonThirdType.apply {
                text = thirdType
                isVisible = thirdType != null
            }

            Glide.with(itemView.context).load(item.imageurl).into(ivPokemonPicture)
        }

        @ColorInt
        fun getPokemonColor(firstType: String?): Int {
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
}