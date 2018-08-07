package com.example.adi_s.catalogmovietwo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adi_s.catalogmovietwo.R;
import com.example.adi_s.catalogmovietwo.network.Contans;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView imageViewPoster;
    TextView textViewTitle, textViewPopularity, textViewOriginalLanguage, textViewOverview, textViewReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageViewPoster = findViewById(R.id.imageView_activity_detail_movie_1_1_1);
        textViewTitle = findViewById(R.id.textView_content_detail_movie_1_1);
        textViewPopularity = findViewById(R.id.textView_content_detail_movie_1_2);
        textViewOriginalLanguage = findViewById(R.id.textView_content_detail_movie_1_3);
        textViewOverview = findViewById(R.id.textView_content_detail_movie_1_4);
        textViewReleaseDate = findViewById(R.id.textView_content_detail_movie_1_5);

        Bundle bundle = getIntent().getExtras();
        String poster = bundle.getString(Contans.POSTER_MOVIE);
        String titleMovie = bundle.getString(Contans.TITLE_MOVIE);
        String popularity = bundle.getString(Contans.POPULARITY);
        String originalTitle = bundle.getString(Contans.ORIGINAL_LANGUAGE);
        String overview = bundle.getString(Contans.OVERVIEM);
        String releaseDate = bundle.getString(Contans.RELEASE_DATE);

        getSupportActionBar().setTitle(titleMovie);
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + poster).into(imageViewPoster);
        textViewPopularity.setText(popularity);
        textViewOriginalLanguage.setText(originalTitle);
        textViewOverview.setText(overview);
        textViewReleaseDate.setText(releaseDate);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
