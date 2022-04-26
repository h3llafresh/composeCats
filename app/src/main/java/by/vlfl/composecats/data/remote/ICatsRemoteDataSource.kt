package by.vlfl.composecats.data.remote

import by.vlfl.composecats.data.remote.model.CatJson

interface ICatsRemoteDataSource {
    suspend fun fetchCats(): List<CatJson>
}