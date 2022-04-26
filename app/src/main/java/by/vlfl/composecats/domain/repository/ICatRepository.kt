package by.vlfl.composecats.domain.repository

import by.vlfl.composecats.domain.entity.CatEntity

interface ICatRepository {
    suspend fun fetchCats(): List<CatEntity>
}