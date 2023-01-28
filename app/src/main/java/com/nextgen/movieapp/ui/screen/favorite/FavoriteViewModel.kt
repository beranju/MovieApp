package com.nextgen.movieapp.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.usecase.MovieUseCase
import com.nextgen.movieapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    private var _uiState: MutableStateFlow<UiState<List<DetailMovieModel>>> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<List<DetailMovieModel>>> get() = _uiState

    fun getFavoriteMovie(){
        viewModelScope.launch {
            movieUseCase.getFavoriteMovie()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ result ->
                    if (result is BaseResult.Success){
                        _uiState.value =UiState.Success(result.data!!)
                    }
                }
        }
    }
}