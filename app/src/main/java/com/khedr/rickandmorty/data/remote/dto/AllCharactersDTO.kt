package com.khedr.rickandmorty.data.remote.dto

import com.khedr.rickandmorty.domain.model.Character

data class AllCharactersDTO(
    val info: Info,
    val results: List<CharacterDTO>
)

fun AllCharactersDTO.toListOfCharacters(): List<Character> {
    val charactersList: ArrayList<Character> = arrayListOf()
    for (characterDTO in results) {
        charactersList.add(characterDTO.toCharacter())
    }
    return charactersList.toList()
}