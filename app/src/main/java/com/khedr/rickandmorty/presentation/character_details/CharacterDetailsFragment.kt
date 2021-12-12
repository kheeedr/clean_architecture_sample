package com.khedr.rickandmorty.presentation.character_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.khedr.rickandmorty.R
import com.khedr.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.khedr.rickandmorty.domain.model.Character
import com.khedr.rickandmorty.presentation.MainViewModel
import com.khedr.rickandmorty.presentation.adapters.FlexAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private lateinit var b: FragmentCharacterDetailBinding

    private val viewModel: MainViewModel by viewModels()
    private val safeArgs: CharacterDetailsFragmentArgs by navArgs()
    private val flexAdapter: FlexAdapter by lazy {
        FlexAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cachedCharacter = safeArgs.cachedCharacter

        b.rvEpisodes.apply {
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.CENTER
            }
            adapter = flexAdapter
        }

        setupView(cachedCharacter)
        viewModel.getCharacterDetails(cachedCharacter.id)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.characterDetailsFlow.collect {
                when {
                    it.character != null -> setupView(it.character)

                    it.error.isNotBlank() -> Toast.makeText(requireContext(),
                        it.error,
                        Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setupView(character: Character) {
        Glide.with(requireContext()).load(character.image).timeout(30000).into(b.ivCharacter)
        b.tvCharacterNameDetails.text = character.name
        b.tvCharacterStatusDetails.text = "Status: " + character.status
        b.tvCharacterGenderDetails.text = "Gender: " + character.gender

        if (!character.species.isNullOrEmpty())
            b.tvCharacterSpeciesDetails.text = "Species: " + character.species
        else b.tvCharacterSpeciesDetails.visibility = View.GONE

        if (!character.type.isNullOrEmpty())
            b.tvCharacterTypeDetails.text = "Type: " + character.type
        else b.tvCharacterTypeDetails.visibility = View.GONE

        flexAdapter.contentList = character.episode ?: emptyList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return b.root
    }


}