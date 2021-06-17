package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodDetail_X extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail__x);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#CFCFCF"));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);


        TextView textView1 = (TextView)findViewById(R.id.text);
        TextView textView2 = (TextView)findViewById(R.id.yn);
        TextView textView3 = (TextView) findViewById(R.id.text3);
        ImageView imageView = (ImageView)findViewById(R.id.food_image);

        Intent intent = getIntent();
        String text = intent.getExtras().getString("key1");
        String yn = intent.getExtras().getString("key2");
        String text3 = intent.getExtras().getString("key3");
        int image = intent.getExtras().getInt("key4");
        textView1.setText(text);
        textView2.setText(yn);
        textView3.setText(text3);
        imageView.setImageResource(image);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}