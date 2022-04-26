package by.vlfl.composecats.data.remote

import by.vlfl.composecats.utils.Constants
import by.vlfl.composecats.data.remote.api.CatsApiService
import javax.inject.Inject

class CatsRemoteDataSource @Inject constructor(private val catsApiService: CatsApiService): ICatsRemoteDataSource {
    override suspend fun fetchCats() = catsApiService.fetchCats(Constants.X_API_KEY)
}