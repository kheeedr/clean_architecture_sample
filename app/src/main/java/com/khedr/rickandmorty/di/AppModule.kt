package com.khedr.rickandmorty.di

import com.khedr.rickandmorty.data.remote.RickApi
import com.khedr.rickandmorty.data.repository.CharactersRepositoryImpl
import com.khedr.rickandmorty.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun getRickApiInstance(): RickApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickApi::class.java)
    }

    @Provides
    @Singleton
    fun getCharactersRepository(api: RickApi): CharactersRepository {
        return CharactersRepositoryImpl(api)
    }
}