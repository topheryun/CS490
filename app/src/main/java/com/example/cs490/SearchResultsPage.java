package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SearchResultsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_page);

        ImageButton recipeImage1 = findViewById(R.id.recipeImage1);
        recipeImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.allrecipes.com/recipe/11466/ultimate-maple-snickerdoodles/";
                Uri webAddress = Uri.parse(url);
                Intent gotoURL = new Intent(Intent.ACTION_VIEW, webAddress);
                if(gotoURL.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoURL);
                }
            }
        });

        ImageButton recipeImage2 = findViewById(R.id.recipeImage2);
        recipeImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.allrecipes.com/recipe/263032/90-second-keto-bread-in-a-mug/";
                Uri webAddress = Uri.parse(url);
                Intent gotoURL = new Intent(Intent.ACTION_VIEW, webAddress);
                if(gotoURL.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoURL);
                }
            }
        });

        ImageButton recipeImage3 = findViewById(R.id.recipeImage3);
        recipeImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.allrecipes.com/recipe/240496/gluten-free-sugar-cookies/";
                Uri webAddress = Uri.parse(url);
                Intent gotoURL = new Intent(Intent.ACTION_VIEW, webAddress);
                if(gotoURL.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoURL);
                }
            }
        });

        Button recipeButton1 = findViewById(R.id.recipeButton1);
        recipeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.allrecipes.com/recipe/11466/ultimate-maple-snickerdoodles/";
                Uri webAddress = Uri.parse(url);
                Intent gotoURL = new Intent(Intent.ACTION_VIEW, webAddress);
                if(gotoURL.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoURL);
                }
            }
        });

        Button recipeButton2 = findViewById(R.id.recipeButton2);
        recipeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.allrecipes.com/recipe/263032/90-second-keto-bread-in-a-mug/";
                Uri webAddress = Uri.parse(url);
                Intent gotoURL = new Intent(Intent.ACTION_VIEW, webAddress);
                if(gotoURL.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoURL);
                }
            }
        });

        Button recipeButton3 = findViewById(R.id.recipeButton3);
        recipeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.allrecipes.com/recipe/240496/gluten-free-sugar-cookies/";
                Uri webAddress = Uri.parse(url);
                Intent gotoURL = new Intent(Intent.ACTION_VIEW, webAddress);
                if(gotoURL.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoURL);
                }
            }
        });
    }
}
