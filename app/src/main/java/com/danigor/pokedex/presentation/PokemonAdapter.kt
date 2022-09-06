package com.danigor.pokedex.presentation

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.danigor.pokedex.R
import com.danigor.pokedex.data.network.model.PokemonResponse

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
    }
}