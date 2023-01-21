package com.stellarworker.movies.di

import com.stellarworker.movies.repository.movies.MoviesRemoteDataSource
import com.stellarworker.movies.repository.movies.MoviesRepository
import com.stellarworker.movies.repository.movies.MoviesRepositoryImpl
import com.stellarworker.movies.ui.movies.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    single { MoviesRemoteDataSource() }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
    viewModel { MainFragmentViewModel(get(), get()) }
}