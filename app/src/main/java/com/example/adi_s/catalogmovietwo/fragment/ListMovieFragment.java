package com.example.adi_s.catalogmovietwo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public ListMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDataListMovie = new ArrayList<>();

        View itemView = inflater.inflate(R.layout.fragment_list_movie, container, false);

        recyclerViewListMovieFragment = itemView.findViewById(R.id.recyclerMovie_fragment_list_movie_1_2_1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerViewListMovieFragment.setLayoutManager(linearLayoutManager);

        ApiMovieInterface apiMovieInterface = ApiMovieClient.createServiceClient(ApiMovieInterface.class);

        Call<DataListMovie> getNoticeListMovie = apiMovieInterface.getDataListMovie("now_playing", getString(R.string.api_key));
        getNoticeListMovie.enqueue(new Callback<DataListMovie>() {
            @Override
            public void onResponse(Call<DataListMovie> call, Response<DataListMovie> response) {
                mDataListMovie = response.body().getResults();
                AdapterListMovie adapterListMovie = new AdapterListMovie(getActivity(), mDataListMovie);
                recyclerViewListMovieFragment.setAdapter(adapterListMovie);
            }

            @Override
            public void onFailure(Call<DataListMovie> call, Throwable t) {
                    Toast.makeText(getActivity(),
                            ""+t.getMessage(),
                            Toast.LENGTH_SHORT).show();

            }
        });
        return itemView;

    }
}
