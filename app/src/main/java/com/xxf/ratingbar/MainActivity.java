package com.xxf.ratingbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xxf.view.ratingbar.XXFRatingBar;

public class MainActivity extends AppCompatActivity {
    XXFRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar = findViewById(R.id.ratingBar);
        findViewById(R.id.test)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        ratingBar.setStarColor(Color.RED);
                        ratingBar.setBgColor(getResources().getColor(R.color.c10_alpha_10));

                        ratingBar.setRating(4);
                        //startActivity(new Intent(view.getContext(), MainActivity.class));
                    }
                });
        findViewById(R.id.test2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ratingBar.setRight2Left(true);
                        ratingBar.setCustomerDrawable(R.drawable.ic_rating_star_border, R.drawable.ic_rating_star_border);
                        ratingBar.setStarColor(Color.YELLOW);
                        ratingBar.setBgColor(Color.GRAY);
                    }
                });

    }
}