package com.khedr.rickandmorty.domain.use_case.get_all_characters

import com.khedr.rickandmorty.common.Resource
import com.khedr.rickandmorty.data.remote.dto.toListOfCharacters
import com.khedr.rickandmorty.domain.model.Character
import com.khedr.rickandmorty.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCharactersUseCase
@Inject
constructor(private val repository: CharactersRepository) {

    operator fun invoke(pageNumber: Int): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = repository.getAllCharacters(pageNumber).toListOfCharacters()
            emit(Resource.Success(characters))
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