package com.khedr.rickandmorty.data.repository

import com.khedr.rickandmorty.data.remote.RickApi
import com.khedr.rickandmorty.data.remote.dto.AllCharactersDTO
import com.khedr.rickandmorty.data.remote.dto.CharacterDTO
import com.khedr.rickandmorty.domain.repository.CharactersRepository
import retrofit2.Response
import javax.inject.Inject

class CharactersRepositoryImpl
@Inject
constructor(private val rickApi: RickApi) : CharactersRepository {

    override suspend fun getAllCharacters(pageNumber: Int) =
        rickApi.getAllCharacters(pageNumber)


    override suspend fun getCharacterDetails(characterId: Int) =
        rickApi.getCharacterDetails(characterId)

}