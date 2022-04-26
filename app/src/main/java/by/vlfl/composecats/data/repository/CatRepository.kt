package by.vlfl.composecats.data.repository

import by.vlfl.composecats.data.remote.CatsRemoteDataSource
import by.vlfl.composecats.data.remote.model.toDomainModel
import by.vlfl.composecats.domain.repository.ICatRepository
import javax.inject.Inject

class CatRepository @Inject constructor(private val catsRemoteDataSource: CatsRemoteDataSource): ICatRepository {

    override suspend fun fetchCats() = catsRemoteDataSource.fetchCats().map { catJson ->
        catJson.toDomainModel()
    }
}