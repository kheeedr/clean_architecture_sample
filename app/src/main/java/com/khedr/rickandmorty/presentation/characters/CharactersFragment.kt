package com.khedr.rickandmorty.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.khedr.rickandmorty.R
import com.khedr.rickandmorty.databinding.FragmentCharactersBinding
import com.khedr.rickandmorty.presentation.MainActivity
import com.khedr.rickandmorty.presentation.MainViewModel
import com.khedr.rickandmorty.presentation.adapters.CharactersAdapter
import com.khedr.rickandmorty.presentation.adapters.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharactersFragment : Fragment(), ItemClickListener {
    private lateinit var b: FragmentCharactersBinding
    private var contentAppeared: Boolean = false
    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(requireContext())
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.rvAllCharacters.adapter = charactersAdapter
        charactersAdapter.itemClickListener = this
        viewModel.getAllCharacters(1)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.charactersFlow.collect {
                if (it.isLoading && !contentAppeared) {
                    b.progressCharacters.visibility = View.VISIBLE
                } else if (!it.characters.isNullOrEmpty()) {

                    b.progressCharacters.visibility = View.GONE
                    charactersAdapter.charactersList = it.characters
                    contentAppeared = true
                } else if (it.error.isNotBlank()) {
                    b.progressCharacters.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    override fun onItemClicked(position: Int) {
        val character = charactersAdapter.charactersList[position]
        val action =
            CharactersFragmentDirections.actionToCharacterDetailsFragment(character)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        return b.root
    }

}