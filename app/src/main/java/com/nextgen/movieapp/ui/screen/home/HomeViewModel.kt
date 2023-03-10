package com.nextgen.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.movieapp.domain.common.BaseResult
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
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    private var _uiState: MutableStateFlow<UiState<List<MovieModel>>> = MutableStateFlow(UiState.Loading)
    val uiState:StateFlow<UiState<List<MovieModel>>> get() = _uiState

    private var _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> get() = _searchText

    init {
        getPopularMovie()
    }

    fun getPopularMovie(){
        viewModelScope.launch() {
            movieUseCase.getPopularMovie()
                .catch {
                    _uiState.value =UiState.Error(it.message.toString())
                }.collect{movieItem->
                    when(movieItem){
                        is BaseResult.Success -> {
                            _uiState.value = UiState.Success(movieItem.data!!)
                        }
                        is BaseResult.Error -> {
                            _uiState.value = UiState.Error(movieItem.message.toString())
                        }
                    }
                }
        }
    }

    private fun getSearchMovie(query: String){
        viewModelScope.launch {
            movieUseCase.getSearchMovie(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{listMovie->
                    when(listMovie){
                        is BaseResult.Success -> {
                            _uiState.value = UiState.Success(listMovie.data!!)
                        }
                        is BaseResult.Error -> {
                            _uiState.value = UiState.Error(listMovie.message.toString())
                        }
                    }
                }
        }
    }

    fun onChangedSearchQuery(changeSearchQuery: String){
        _searchText.value = changeSearchQuery
        if (changeSearchQuery.isEmpty()){
            getPopularMovie()
        }
        getSearchMovie(changeSearchQuery)
    }

    fun onClearClick(){
        _searchText.value = ""
        getPopularMovie()
    }

}