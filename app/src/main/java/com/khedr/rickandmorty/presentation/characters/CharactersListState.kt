package com.khedr.rickandmorty.presentation.characters

import com.khedr.rickandmorty.domain.model.Character

data class CharactersListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String = ""
)
