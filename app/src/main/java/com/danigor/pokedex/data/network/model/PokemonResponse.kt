package com.danigor.pokedex.data.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "abilities")
    val abilities: List<String>,
    @Json(name = "attack")
    val attack: Int,
    @Json(name = "base_exp")
    val baseExp: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "cycles")
    val cycles: String,
    @Json(name = "defense")
    val defense: Int,
    @Json(name = "egg_groups")
    val eggGroups: String,
    @Json(name = "evolutions")
    val evolutions: List<String>,
    @Json(name = "evolvedfrom")
    val evolvedfrom: String,
    @Json(name = "female_percentage")
    val femalePercentage: String,
    @Json(name = "genderless")
    val genderless: Int,
    @Json(name = "height")
    val height: String,
    @Json(name = "hp")
    val hp: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "imageurl")
    val imageurl: String,
    @Json(name = "male_percentage")
    val malePercentage: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "reason")
    val reason: String,
    @Json(name = "special_attack")
    val specialAttack: Int,
    @Json(name = "special_defense")
    val specialDefense: Int,
    @Json(name = "speed")
    val speed: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "typeofpokemon")
    val typeofpokemon: List<String>,
    @Json(name = "weaknesses")
    val weaknesses: List<String>,
    @Json(name = "weight")
    val weight: String,
    @Json(name = "xdescription")
    val xdescription: String,
    @Json(name = "ydescription")
    val ydescription: String
)