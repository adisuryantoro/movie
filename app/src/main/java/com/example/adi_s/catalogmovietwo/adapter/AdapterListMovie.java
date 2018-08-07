package com.example.adi_s.catalogmovietwo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adi_s.catalogmovietwo.R;
import com.example.adi_s.catalogmovietwo.activity.DetailMovieActivity;
import com.example.adi_s.catalogmovietwo.model.Result;
import com.example.adi_s.catalogmovietwo.network.Contans;

import java.util.List;

public class AdapterListMovie extends RecyclerView.Adapter<AdapterListMovie.ListMovieViewHolder> {
    private Context mContext;
    private List<Result> dataListMovieList;

    public AdapterListMovie(Context mContext, List<Result> dataListMovieList){
        this.mContext = mContext;
        this.dataListMovieList = dataListMovieList;
    }

    @NonNull
    @Override
    public AdapterListMovie.ListMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lits_movie_item, parent, false);
        return new ListMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieViewHolder holder, final int position) {
        String linkGambar = dataListMovieList.get(position).getPosterPath();
        Glide.with(mContext)
                .load("http://image.tmdb.org/t/p/w185/" + linkGambar)
                .into(holder.imageViewPoster);

        holder.textViewTitle.setText(dataListMovieList.get(position).getTitle());
        holder.textViewPopularity.setText(dataListMovieList.get(position).getPopularity()+"");
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Contans.POSTER_MOVIE, dataListMovieList.get(position).getPosterPath());
                bundle.putString(Contans.TITLE_MOVIE, dataListMovieList.get(position).getTitle());
                bundle.putString(Contans.POPULARITY, dataListMovieList.get(position).getPopularity()+"");
                bundle.putString(Contans.ORIGINAL_LANGUAGE, dataListMovieList.get(position).getOriginalLanguage());
                bundle.putString(Contans.OVERVIEM, dataListMovieList.get(position).getOverview());
                bundle.putString(Contans.RELEASE_DATE, dataListMovieList.get(position).getReleaseDate());

                Intent intent = new Intent(mContext, DetailMovieActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }


    public class ListMovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewPopularity;
        Button buttonDetail;

        public ListMovieViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageView_list_movie_item_1_1_1);
            textViewTitle = itemView.findViewById(R.id.textView_list_movie_item_1_1_2);
            textViewPopularity = itemView.findViewById(R.id.textView_list_movie_item_1_1_3);
            buttonDetail = itemView.findViewById(R.id.button_list_movie_item_1_1_4);
        }
    }

    @Override
    public int getItemCount() {
        if (dataListMovieList!=null){
            return dataListMovieList.size();
        }

        else return 0;
    }
}
