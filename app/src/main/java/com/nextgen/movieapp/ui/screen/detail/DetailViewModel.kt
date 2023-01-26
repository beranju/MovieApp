package com.nextgen.movieapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.usecase.MovieUseCase
import com.nextgen.movieapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    private var _state: MutableStateFlow<UiState<DetailMovieResponse>> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<DetailMovieResponse>> get() = _state

    fun getDetailMovieById(id: Int){
        viewModelScope.launch {
            movieUseCase.getDetailMovieById(id)
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect{detailData->
                    when(detailData){
                        is BaseResult.Success -> {
                            _state.value = UiState.Success(detailData.data!!)
                        }
                        is BaseResult.Error -> {
                            _state.value = UiState.Error(detailData.message.toString())
                        }
                    }
                }
        }
    }
}