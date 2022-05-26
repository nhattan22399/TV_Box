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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularHolder> {

    public Context mContext;
    public List<Movie> mMovieList;
    public IMovieUpdate iMovieUpdate;
    public RecyclerViewListen listen;

    public PopularAdapter(Context mContext, List<Movie> mMovieList, IMovieUpdate iMovieUpdate, RecyclerViewListen listen) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        this.iMovieUpdate = iMovieUpdate;
        this.listen = listen;
    }

    @NonNull
    @Override
    public PopularHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular,parent, false);
        return new PopularAdapter.PopularHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularHolder holder, int position) {
        Movie mMovie = mMovieList.get(position);
        holder.txtname.setText(mMovie.title);
        holder.txtdate.setText(mMovie.date);
        holder.rating.setText(mMovie.VOTE);
        Picasso.with(mContext).load(Config.BASE_BACKDROP_PATH + mMovie.backdropPath).into(holder.img);
    }
    public interface RecyclerViewListen{
        void onClick(View v, int position);
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    public class PopularHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView img;
        public TextView txtname,txtdate,rating;
        public PopularHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txt_name);
            txtdate = itemView.findViewById(R.id.txt_date);
            rating = itemView.findViewById(R.id.vote);
            img = itemView.findViewById(R.id.slide_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listen.onClick(view,getAdapterPosition());
        }
    }
}
