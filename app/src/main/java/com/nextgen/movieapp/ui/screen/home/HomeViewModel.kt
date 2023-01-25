package com.nextgen.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.movieapp.data.source.MovieRepository
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: MovieRepository): ViewModel() {
    private var _uiState: MutableStateFlow<UiState<List<ResultsItem>>> = MutableStateFlow(UiState.Loading)
    val uiState:StateFlow<UiState<List<ResultsItem>>> get() = _uiState

    fun getPopularMovie(){
        viewModelScope.launch {
            repository.getPopularMovie()
                .catch {
                    _uiState.value =UiState.Error(it.message.toString())
                }.collect{movieItem->
                    when(movieItem){
                        is BaseResult.Success -> {
                            _uiState.value = UiState.Success(movieItem.data)
                        }
                        is BaseResult.Error -> {
                            _uiState.value = UiState.Error(movieItem.message)
                        }
                    }
                }
        }
    }
}