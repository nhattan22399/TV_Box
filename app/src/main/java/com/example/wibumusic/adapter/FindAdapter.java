package com.example.wibumusic.adapter;

import static com.example.wibumusic.untils.Config.BASE_BACKDROP_PATH;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {
    public Context mContext;
    public List<Movie> mFINDList;
    public IMovieUpdate iMovieUpdate;
    public MovieAdapter.RecyclerViewListen listen;

    public FindAdapter(Context mContext, List<Movie> mFINDList, IMovieUpdate iMovieUpdate) {
        this.mContext = mContext;
        this.mFINDList = mFINDList;
        this.iMovieUpdate = iMovieUpdate;
        this.listen = listen;
    }

    @NonNull

    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find,parent, false);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        Movie mMovie = mFINDList.get(position);
        holder.txtfind.setText(mMovie.name);
        Picasso.with(mContext).load(BASE_BACKDROP_PATH + mMovie.backdropPath).into(holder.imgfind);
    }
    public interface RecyclerViewListen{
        void onClick(View v, int position);
    }
    @Override
    public int getItemCount() {
        return mFINDList.size();
    }

    public class FindViewHolder extends RecyclerView.ViewHolder {
        ImageView imgfind;
        TextView txtfind;


        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            imgfind = itemView.findViewById(R.id.imageViewfind);
            txtfind = itemView.findViewById(R.id.textViewfind);
        }
    }


}
