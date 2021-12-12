package com.khedr.rickandmorty.domain.repository

import com.khedr.rickandmorty.data.remote.dto.AllCharactersDTO
import com.khedr.rickandmorty.data.remote.dto.CharacterDTO
import retrofit2.Response

interface CharactersRepository {
    suspend fun getAllCharacters(pageNumber: Int): AllCharactersDTO

    suspend fun getCharacterDetails(characterId: Int): CharacterDTO

}