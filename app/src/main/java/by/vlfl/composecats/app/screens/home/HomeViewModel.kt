package by.vlfl.composecats.app.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vlfl.composecats.app.screens.home.views.CatItemModel
import by.vlfl.composecats.app.screens.home.views.toCardItemModel
import by.vlfl.composecats.domain.usecase.FetchCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchCatsUseCase: FetchCatsUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState.Loading)
    val uiState: StateFlow<HomeViewState> = _uiState

    init {
        refreshCats()
    }

    fun handleUiEvent(uiEvent: HomeUiEvent) = when (uiEvent) {
        is HomeUiEvent.OpenCatDetailsEvent -> openCatDetails(uiEvent.cat)
    }

    private fun refreshCats() {
        _uiState.value = HomeViewState.Loading
        viewModelScope.launch {
            try {
                val cats = fetchCatsUseCase().map {
                    it.toCardItemModel()
                }

                if (cats.isNullOrEmpty()) {
                    _uiState.value = HomeViewState.NoCats
                } else {
                    _uiState.value = HomeViewState.HasCats(cats)
                }

            } catch (ex: Throwable) {
                ex.printStackTrace()
                _uiState.value = HomeViewState.Error
            }
        }
    }

    fun openCatDetails(cat: CatItemModel) {

    }
}