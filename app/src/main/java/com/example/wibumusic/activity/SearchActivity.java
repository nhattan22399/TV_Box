package com.example.wibumusic.activity;

import static com.example.wibumusic.untils.Config.BASE_URL_API;
import static com.example.wibumusic.untils.Config.BASE_URL_API_HOT;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wibumusic.R;
import com.example.wibumusic.adapter.MovieAdapter;
import com.example.wibumusic.adapter.FindAdapter;
import com.example.wibumusic.data.response.Movie;
import com.example.wibumusic.data.response.MovieResponse;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rcvfind;
    private com.example.wibumusic.adapter.FindAdapter FindAdapter;
    List<Movie> movieList;
    ImageButton IbSearch;
    EditText edtSeach;
    BottomNavigationItemView account,home,popular,search;
    public FindAdapter.RecyclerViewListen listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setOnClickListen();

        rcvfind = findViewById(R.id.rcv_find);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        rcvfind.setLayoutManager(linearLayoutManager);

        edtSeach = findViewById(R.id.EdtSearch);

        IbSearch = findViewById(R.id.btnSe);

        account= findViewById(R.id.nav_canhan);
        home = findViewById(R.id.nav_home);
        popular = findViewById(R.id.nav_ani);
        search = findViewById(R.id.nav_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,PopularActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });

        IbSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSeach.getVisibility()== View.VISIBLE){
                    edtSeach.setVisibility(View.GONE);
                }else {
                    edtSeach.setVisibility(View.VISIBLE);
                }
            }
        });
        update();
    }

    private void setOnClickListen() {
        listen = new FindAdapter.RecyclerViewListen() {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        return super.onCreateOptionsMenu(menu);
    }

    public void update() {
        new OKHTTPReqTask().execute();
    }

    private class OKHTTPReqTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            try {
                Request.Builder builder = new Request.Builder();
                builder.url(BASE_URL_API_HOT);
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
            FindAdapter = new FindAdapter(getApplicationContext(), movieResponse.movies, SearchActivity.this::update);
            rcvfind.setAdapter(FindAdapter);
            FindAdapter.notifyDataSetChanged();




        }
    }
}