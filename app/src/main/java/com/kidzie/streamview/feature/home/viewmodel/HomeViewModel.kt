package com.kidzie.streamview.feature.home.viewmodel

import com.kidzie.streamview.core.BaseViewModel
import com.kidzie.streamview.core.Effect
import com.kidzie.streamview.core.Event
import com.kidzie.streamview.core.Result
import com.kidzie.streamview.core.ViewState
import com.kidzie.streamview.datasource.model.Movie
import com.kidzie.streamview.usecase.GetNowPlayingUseCase

class HomeViewModel(private val getNowPlayingUseCase: GetNowPlayingUseCase) :
    BaseViewModel<HomeEvent, HomeViewState, HomeEffect>(HomeViewState()) {


    override fun reduce(
        event: HomeEvent, currentState: HomeViewState
    ): HomeViewState {
        return currentState
    }

    override suspend fun handleSideEffect(event: HomeEvent, newState: HomeViewState) {
        when (event) {
            is HomeEvent.LoadData -> loadNowPlayingMovies()
            is HomeEvent.OnItemClicked -> {
                TODO()
            }
        }
    }

    private suspend fun loadNowPlayingMovies() {
        withLoading {
            when (val result = getNowPlayingUseCase()) {
                is Result.Success -> updateState {
                    it.copy(data = result.data, error = null)
                }
                is Result.Error -> updateState {
                    it.copy(error = result.message ?: result.exception.message ?: "Unknown error")
                }
            }
        }
    }
}

data class HomeViewState(
    val data: List<Movie> = emptyList(),
    val error: String? = null
) : ViewState

sealed class HomeEvent : Event {
    object LoadData : HomeEvent()
    data class OnItemClicked(val item: String) : HomeEvent()
}

sealed class HomeEffect : Effect {
    data class NavigateToDetail(val idMovie : Int) : HomeEffect()
}