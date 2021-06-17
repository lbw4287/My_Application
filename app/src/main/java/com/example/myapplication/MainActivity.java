package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#CFCFCF"));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageButton btnfruit = (ImageButton) findViewById(R.id.btn_fruit);
        ImageButton btnveg = (ImageButton) findViewById(R.id.btn_veg);
        ImageButton btnmeat= (ImageButton) findViewById(R.id.btn_meat);
        ImageButton btnfish= (ImageButton) findViewById(R.id.btn_fish);
        ImageButton btnnuts= (ImageButton) findViewById(R.id.btn_nuts);

        btnfruit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FruitList.class);
                startActivity(intent);
                finish();
            }
        });

        btnveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VegList.class);
                startActivity(intent);
                finish();
            }

        });
        btnmeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MeatList.class);
                startActivity(intent);
                finish();
            }
        });
        btnfish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FishList.class);
                startActivity(intent);
                finish();
            }
        });
        btnnuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NutsList.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;
        if (gapTime >= 0 && gapTime <= 2000) {
            super.onBackPressed();
        } else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}