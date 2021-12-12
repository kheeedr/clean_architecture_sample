package com.khedr.rickandmorty.data.remote.dto

import com.khedr.rickandmorty.common.Utils
import com.khedr.rickandmorty.domain.model.Character

data class CharacterDTO(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
fun CharacterDTO.toCharacter() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    episode = Utils.getLastIndexesFromUrls(episode)

)