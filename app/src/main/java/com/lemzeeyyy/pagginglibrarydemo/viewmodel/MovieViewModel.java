package com.lemzeeyyy.pagginglibrarydemo.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.lemzeeyyy.pagginglibrarydemo.model.Movie;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {
    public Flowable<PagingData<Movie>> moviePagingDataFlow;
    public MovieViewModel(){
        init();
    }

    private void init() {

        /*
         1. Define Paging Source
         2. Creating new pager
         3. Initiate Flow
        */
        MoviePagingSource moviePagingSource = new MoviePagingSource();

        Pager<Integer, Movie> pager = new Pager(
                new PagingConfig(
                        20,
                        20,
                        false,
                        20,
                        20*499
                ),
                () -> moviePagingSource);

        // Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);

    }
}
