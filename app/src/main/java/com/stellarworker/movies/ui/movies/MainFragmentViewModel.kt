package com.stellarworker.movies.ui.movies

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stellarworker.movies.R
import com.stellarworker.movies.data.SingleLiveEvent
import com.stellarworker.movies.domain.AppMessage
import com.stellarworker.movies.domain.Top250MoviesDTO
import com.stellarworker.movies.repository.movies.MoviesRepository
import com.stellarworker.movies.utils.Mappers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel(
    private val application: Application,
    private val repository: MoviesRepository
) : ViewModel() {
    private val _messagesLiveData = SingleLiveEvent<AppMessage>()
    val messagesLiveData: LiveData<AppMessage> by this::_messagesLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        error.printStackTrace()
    }
    private var queryJob: Job? = null
    private val mainScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + coroutineExceptionHandler)

    private val callBack = object : Callback<Top250MoviesDTO> {
        private val mappers = Mappers()

        override fun onResponse(call: Call<Top250MoviesDTO>, response: Response<Top250MoviesDTO>) {
            val top250MoviesDTO: Top250MoviesDTO? = response.body()
            top250MoviesDTO?.let { top250MoviesDTONotNull ->
                _messagesLiveData.postValue(
                    AppMessage.Top250Movies(mappers.map(top250MoviesDTONotNull))
                )
            } ?: run {
                _messagesLiveData.postValue(
                    AppMessage.InfoSnackBar(application.getString(R.string.message_data_corrupted))
                )
            }
        }

        override fun onFailure(call: Call<Top250MoviesDTO>, t: Throwable) {
            _messagesLiveData.postValue(
                AppMessage.InfoSnackBar(application.getString(R.string.message_network_error))
            )
        }
    }

    fun getMovies() {
        queryJob?.cancel()
        queryJob = mainScope.launch {
            repository.getMoviesFromServer(callBack)
        }
    }

}