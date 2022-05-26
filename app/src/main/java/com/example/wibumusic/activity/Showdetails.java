package com.example.wibumusic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wibumusic.R;
import com.example.wibumusic.data.response.Movie;
import com.example.wibumusic.untils.Config;
import com.squareup.picasso.Picasso;

public class Showdetails extends AppCompatActivity {
    ImageView roundedImageView,imgQC;
    TextView txtTittle, txtId,txtnd,txtName,txtvote;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.wibumusic.R.layout.activity_showdetails);
        txtTittle = findViewById(R.id.tx_namedetail);
        txtName = findViewById(R.id.tx_tx_NAMEdetail);
        txtvote = findViewById(R.id.tx_scoredetail);
        txtId = findViewById(R.id.tx_iddetail);
        txtnd = findViewById(R.id.tx_noidung);
        roundedImageView = findViewById(R.id.imge_details);
        imgQC = findViewById(R.id.viewpar);
        imgBack = findViewById(R.id.imgback);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Showdetails.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Movie Mmovie =(Movie) bundle.get("movie");
        txtTittle.setText("" + Mmovie.title);
        txtName.setText("("+Mmovie.name+")");
        txtId.setText("" + Mmovie.date +"");
        txtvote.setText(""+Mmovie.VOTE);
        txtnd.setText("OverView: " +Mmovie.Noidung);


        Picasso.with(getApplicationContext()).load(Config.BASE_BACKDROP_PATH + Mmovie.Avatar).into(roundedImageView);
        Picasso.with(getApplicationContext()).load(Config.BASE_BACKDROP_PATH + Mmovie.backdropPath).into(imgQC);

    }
}