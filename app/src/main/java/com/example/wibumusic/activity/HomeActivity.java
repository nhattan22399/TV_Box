package com.example.wibumusic.activity;

import static com.example.wibumusic.untils.Config.BASE_URL_API;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wibumusic.R;
import com.example.wibumusic.adapter.MovieAdapter;
import com.example.wibumusic.adapter.SlideAdapter;
import com.example.wibumusic.data.response.Movie;
import com.example.wibumusic.data.response.MovieResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rcv, slide_rcv;
    private MovieAdapter adapter;
    private SlideAdapter slideAdapter;
    ImageView imgsearch;
    BottomNavigationItemView account,home,popular,search;
    public MovieAdapter.RecyclerViewListen listen;
    public SlideAdapter.RecyclerViewListen slide_listen;
    List<Movie> movieList;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView txtuser;
    CircleImageView avataruser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setAdapter();
        setOnClickListen();
        update();

        avataruser = findViewById(R.id.avatar_user);
        txtuser = findViewById(R.id.nameuser);
        imgsearch = findViewById(R.id.imgseach);
        account= findViewById(R.id.nav_canhan);
        home = findViewById(R.id.nav_home);
        popular = findViewById(R.id.nav_ani);
        search = findViewById(R.id.nav_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PopularActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String Name = account.getDisplayName();
            Uri Photo = account.getPhotoUrl();

            txtuser.setText("Hello "+Name+" !");
            Glide.with(this).load(String.valueOf(Photo)).into(avataruser);

        }
    }

    private void setAdapter() {slide_rcv = findViewById(R.id.slide_rcv);
        rcv = findViewById(R.id.rcv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        rcv.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false);
        slide_rcv.setLayoutManager(linearLayoutManager);


        final int interval_time = 3000;
        Handler handler = new Handler();
        Runnable  runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count < 15){

                    slide_rcv.scrollToPosition(count++);
                    handler.postDelayed(this,interval_time);
                    if(count== 15){
                        count=0;
                    }
                }
            }
        };
        handler.postDelayed(runnable,interval_time);

    }
    private void setOnClickListen() {
        listen = new MovieAdapter.RecyclerViewListen() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Showdetails.class);
                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movieList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
    }


    public void update() {
        new OKHTTPReqTask().execute();
    }

    class OKHTTPReqTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            try {
                Request.Builder builder = new Request.Builder();
                builder.url(BASE_URL_API);
                Request request = builder.build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                Log.e("App error", e.toString());
            }

            return "[]";
        }

        @Override
        protected void onPostExecute(String data) {
            Log.e("finish", "good");
            Gson gson = new Gson();
            Type type = new TypeToken<MovieResponse>(){}.getType();
            MovieResponse movieResponse = gson.fromJson(data, type);
            movieList = movieResponse.movies;
            slideAdapter = new SlideAdapter(getApplicationContext(), movieResponse.movies,HomeActivity.this::update,slide_listen);
            adapter = new MovieAdapter(getApplicationContext(), movieResponse.movies, HomeActivity.this::update,listen);
            slide_rcv.setAdapter(slideAdapter);
            rcv.setAdapter(adapter);

            slideAdapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
            slide_rcv.scheduleLayoutAnimation();

        }
    }

}