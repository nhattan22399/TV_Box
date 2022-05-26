package com.example.wibumusic.activity;

import static com.example.wibumusic.untils.Config.BASE_URL_API;
import static com.example.wibumusic.untils.Config.BASE_URL_API_HOT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wibumusic.R;
import com.example.wibumusic.adapter.MovieAdapter;
import com.example.wibumusic.adapter.PopularAdapter;
import com.example.wibumusic.adapter.SlideAdapter;
import com.example.wibumusic.adapter.ViewPagerAdaper;
import com.example.wibumusic.data.response.Movie;
import com.example.wibumusic.data.response.MovieResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PopularActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    BottomNavigationItemView account,home,popular,search;
    private RecyclerView rcv;
    List<Movie> movieList;
    private ViewPagerAdaper viewPagerAdaper;
    private PopularAdapter popularAdapter;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    CircleImageView avataruser;
    public PopularAdapter.RecyclerViewListen listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);


        mTabLayout = findViewById(R.id.talayout);
        mViewPager = findViewById(R.id.view_pager);
        avataruser = findViewById(R.id.avatar_user);
        FragmentManager fragmentManager = getSupportFragmentManager();


        viewPagerAdaper = new ViewPagerAdaper(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdaper);
        mTabLayout.setupWithViewPager(mViewPager);
            mTabLayout.getTabAt(0).setIcon(R.drawable.ic_corn);
            mTabLayout.getTabAt(1).setIcon(R.drawable.ic_fire1);


        account= findViewById(R.id.nav_canhan);
        home = findViewById(R.id.nav_home);
        popular = findViewById(R.id.nav_ani);
        search = findViewById(R.id.nav_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularActivity.this,PopularActivity.class);
                startActivity(intent);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopularActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Uri Photo = account.getPhotoUrl();
            Glide.with(this).load(String.valueOf(Photo)).into(avataruser);

        }

    }



    }