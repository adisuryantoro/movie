package com.example.adi_s.catalogmovietwo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adi_s.catalogmovietwo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends Fragment {


    public DetailMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false);
    }

}
