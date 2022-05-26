package com.example.wibumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wibumusic.R;
import com.example.wibumusic.actions.IMovieUpdate;
import com.example.wibumusic.data.response.Movie;
import com.example.wibumusic.untils.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    public Context mContext;
    public List<Movie> mMovieList;
    public IMovieUpdate iMovieUpdate;
    public RecyclerViewListen listen;

    public MovieAdapter(Context mContext, List<Movie> mSongList, IMovieUpdate iMovieUpdate, RecyclerViewListen listen) {
        this.mContext = mContext;
        this.mMovieList = mSongList;
        this.iMovieUpdate = iMovieUpdate;
        this.listen = listen;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie mMovie = mMovieList.get(position);
        holder.txpercent.setText(mMovie.VOTE+"%");

        Picasso.with(mContext).load(Config.BASE_BACKDROP_PATH + mMovie.Avatar).into(holder.img);
    }
    public interface RecyclerViewListen{
        void onClick(View v, int position);
    }
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img, imgscore;
        public TextView txpercent;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_thumbnail);
            txpercent = itemView.findViewById(R.id.tv_percent);
            imgscore = itemView.findViewById(R.id.imgpercent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listen.onClick(view,getAdapterPosition());
        }
    }
}
