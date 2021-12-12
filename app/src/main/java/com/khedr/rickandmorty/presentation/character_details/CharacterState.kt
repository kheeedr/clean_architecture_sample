package com.khedr.rickandmorty.presentation.character_details

import com.khedr.rickandmorty.domain.model.Character

data class CharacterState(
    val character: Character? = null,
    val error: String = ""
)
