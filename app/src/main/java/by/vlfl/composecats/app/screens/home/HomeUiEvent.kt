package by.vlfl.composecats.app.screens.home

import by.vlfl.composecats.app.screens.home.views.CatItemModel

sealed interface HomeUiEvent {
    data class OpenCatDetailsEvent(val cat: CatItemModel): HomeUiEvent
}