package com.example.wibumusic.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("release_date")
    public String date;

    @SerializedName("original_title")
    public String title;

    @SerializedName("vote_average")
    public String VOTE;

    @SerializedName("title")
    public String name;

    @SerializedName("poster_path")
    public String Avatar;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("overview")
    public String Noidung;

    protected Movie(Parcel in) {

        date = in.readString();
        title = in.readString();
        VOTE = in.readString();
        name = in.readString();
        Avatar = in.readString();
        backdropPath = in.readString();
        Noidung = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeString(VOTE);
        parcel.writeString(name);
        parcel.writeString(Avatar);
        parcel.writeString(backdropPath);
        parcel.writeString(Noidung);
    }
}
