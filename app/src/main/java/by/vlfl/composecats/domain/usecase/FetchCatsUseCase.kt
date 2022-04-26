package by.vlfl.composecats.domain.usecase

import by.vlfl.composecats.domain.repository.ICatRepository
import javax.inject.Inject

class FetchCatsUseCase @Inject constructor(private val catsRepository: ICatRepository) {
    suspend operator fun invoke() = catsRepository.fetchCats()
}