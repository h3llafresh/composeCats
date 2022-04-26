package by.vlfl.composecats.app.screens.home

import by.vlfl.composecats.app.screens.home.views.CatItemModel

sealed interface HomeViewState {
    object Loading : HomeViewState
    object Error: HomeViewState
    data class HasCats(
        val cats: List<CatItemModel>
    ) : HomeViewState
    object NoCats: HomeViewState
}