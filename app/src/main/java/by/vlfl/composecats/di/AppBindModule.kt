package by.vlfl.composecats.di

import by.vlfl.composecats.data.remote.CatsRemoteDataSource
import by.vlfl.composecats.data.remote.ICatsRemoteDataSource
import by.vlfl.composecats.data.repository.CatRepository
import by.vlfl.composecats.domain.repository.ICatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {
    @Binds
    fun bindCatsRepository(catsRepository: CatRepository): ICatRepository

    @Binds
    fun bindCatsRemoteDataSource(catsRemoteDataSource: CatsRemoteDataSource): ICatsRemoteDataSource
}