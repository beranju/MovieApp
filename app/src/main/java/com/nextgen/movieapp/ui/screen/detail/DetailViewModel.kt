package com.nextgen.movieapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.usecase.MovieUseCase
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.utils.DataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    private var _state: MutableStateFlow<UiState<DetailMovieModel>> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<DetailMovieModel>> get() = _state

    private var _isFavorite : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite : StateFlow<Boolean> get() = _isFavorite

    fun getDetailMovieById(id: Int){
        viewModelScope.launch {
            movieUseCase.getDetailMovieById(id)
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect{ detailData->
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

    fun isFavoriteMovie(movieId: Int){
        viewModelScope.launch {
            movieUseCase.isFavoriteMovie(movieId)
                .collect{
                    if (it is BaseResult.Success){
                        _isFavorite.value = it.data!!
                    }
                }
        }
    }

    fun insertFavoriteMovie(movieModel: DetailMovieModel){
        viewModelScope.launch {
            movieUseCase.insertFavoriteMovie(movieModel)
        }
    }
}