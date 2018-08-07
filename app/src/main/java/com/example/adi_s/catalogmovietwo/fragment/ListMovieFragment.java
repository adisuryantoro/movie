package com.example.adi_s.catalogmovietwo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.adi_s.catalogmovietwo.R;
import com.example.adi_s.catalogmovietwo.adapter.AdapterListMovie;
import com.example.adi_s.catalogmovietwo.model.DataListMovie;
import com.example.adi_s.catalogmovietwo.model.Result;
import com.example.adi_s.catalogmovietwo.network.ApiMovieClient;
import com.example.adi_s.catalogmovietwo.network.ApiMovieInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMovieFragment extends Fragment {
    RecyclerView recyclerViewListMovieFragment;
    List<Result> mDataListMovie;
    SwipeRefreshLayout swipeRefreshLayoutListMovieFragment;
    LinearLayoutManager linearLayoutManager;
    AdapterListMovie adapterListMovie;


    public ListMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDataListMovie = new ArrayList<>();

        View itemView = inflater.inflate(R.layout.fragment_list_movie, container, false);

        recyclerViewListMovieFragment = itemView.findViewById(R.id.recyclerMovie_fragment_list_movie_1_1_2);
        swipeRefreshLayoutListMovieFragment = itemView.findViewById(R.id.swipeRfreshLayout_fragment_list_movie_1);
        adapterListMovie = new AdapterListMovie(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListMovieFragment.setLayoutManager(linearLayoutManager);
        recyclerViewListMovieFragment.setAdapter(adapterListMovie);

        doApiCall();

        swipeRefreshLayoutListMovieFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapterListMovie.clearData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        doApiCall();

                    }
                }, 1000);
            }
        });
        return itemView;

    }

    private void doApiCall() {
        ApiMovieInterface apiMovieInterface = ApiMovieClient.createServiceClient(ApiMovieInterface.class);

        Call<DataListMovie> getNoticeListMovie = apiMovieInterface.getDataListMovie("now_playing", getString(R.string.api_key));
        getNoticeListMovie.enqueue(new Callback<DataListMovie>() {

            @Override
            public void onResponse(Call<DataListMovie> call, Response<DataListMovie> response) {
                swipeRefreshLayoutListMovieFragment.setRefreshing(false);
                mDataListMovie = response.body().getResults();
                adapterListMovie.setDataListMovieList(mDataListMovie);
            }

            @Override
            public void onFailure(Call<DataListMovie> call, Throwable t) {
                Toast.makeText(getActivity(),
                        "" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
