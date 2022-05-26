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

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {
    public Context mContext;
    public List<Movie> mMovieList;
    public IMovieUpdate iMovieUpdate;
    public RecyclerViewListen listen;


    public SlideAdapter(Context mContext, List<Movie> mMovieList, IMovieUpdate iMovieUpdate, RecyclerViewListen listen) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        this.iMovieUpdate = iMovieUpdate;
        this.listen = listen;
    }

    @NonNull
    @Override
    public SlideAdapter.SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.wibumusic.R.layout.slide_item,parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideAdapter.SlideViewHolder holder, int position) {
        Movie mMovie = mMovieList.get(position);
        holder.txtSlide.setText(mMovie.title);
        holder.txtslide_date.setText(mMovie.date);

        Picasso.with(mContext).load(Config.BASE_BACKDROP_PATH + mMovie.backdropPath).into(holder.img);
    }
    public interface RecyclerViewListen{
        void onClick(View v, int position);
    }
    @Override
    public int getItemCount() {
         return mMovieList.size();
    }

    public class SlideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView img;
        public TextView txtSlide,txtslide_date;
        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSlide = itemView.findViewById(R.id.txt_slide);
            txtslide_date = itemView.findViewById(R.id.txt_slide_date);
            img = itemView.findViewById(R.id.slide_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listen.onClick(view,getAdapterPosition());
        }
    }
}
