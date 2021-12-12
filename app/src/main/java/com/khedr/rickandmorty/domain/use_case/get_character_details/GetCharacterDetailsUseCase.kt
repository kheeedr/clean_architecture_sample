package com.khedr.rickandmorty.domain.use_case.get_character_details

import com.khedr.rickandmorty.common.Resource
import com.khedr.rickandmorty.data.remote.dto.toCharacter
import com.khedr.rickandmorty.domain.model.Character
import com.khedr.rickandmorty.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterDetailsUseCase
@Inject
constructor(private val repository: CharactersRepository){

    operator fun invoke(characterId:Int) :Flow<Resource<Character>> = flow{

        try {
            emit(Resource.Loading())
            val characterDetails = repository.getCharacterDetails(characterId).toCharacter()
            emit(Resource.Success(characterDetails))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }

    }

}