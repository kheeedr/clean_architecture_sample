package com.khedr.rickandmorty.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khedr.rickandmorty.common.Resource
import com.khedr.rickandmorty.domain.use_case.get_all_characters.GetAllCharactersUseCase
import com.khedr.rickandmorty.domain.use_case.get_character_details.GetCharacterDetailsUseCase
import com.khedr.rickandmorty.presentation.character_details.CharacterState
import com.khedr.rickandmorty.presentation.characters.CharactersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private var _charactersFlow: MutableStateFlow<CharactersListState> =
        MutableStateFlow(CharactersListState())
    val charactersFlow: StateFlow<CharactersListState> = _charactersFlow

    fun getAllCharacters(pageNumber: Int) {
        viewModelScope.launch {
            getAllCharactersUseCase(pageNumber).collect {
                when (it) {
                    is Resource.Loading -> _charactersFlow.value = CharactersListState(true)
                    is Resource.Success -> _charactersFlow.value =
                        CharactersListState(characters = it.data ?: emptyList())
                    is Resource.Error -> {
                        _charactersFlow.value =
                            CharactersListState(
                                error = it.message ?: "An unexpected error occurred"
                            )
                    }
                }
            }
        }
    }

    //============================================================================

    private var _characterDetailsFlow: MutableStateFlow<CharacterState> =
        MutableStateFlow(CharacterState())
    val characterDetailsFlow: StateFlow<CharacterState> = _characterDetailsFlow


    fun getCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(characterId).collect {
                when (it) {
                    is Resource.Success -> {
                        _characterDetailsFlow.value = CharacterState(character = it.data)
                    }
                    is Resource.Error -> {
                        _characterDetailsFlow.value =
                            CharacterState(error = it.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }
    }

}