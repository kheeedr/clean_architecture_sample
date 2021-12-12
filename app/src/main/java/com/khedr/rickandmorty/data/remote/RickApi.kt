package com.khedr.rickandmorty.data.remote

import com.khedr.rickandmorty.data.remote.dto.AllCharactersDTO
import com.khedr.rickandmorty.data.remote.dto.CharacterDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickApi {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") pageNumber: Int): AllCharactersDTO

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): CharacterDTO

}