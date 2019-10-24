package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Search Button -> Search ingredient page
        ImageButton searchButton = findViewById(R.id.imageButton_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchPageIntent = new Intent(getApplicationContext(), IngredientSearch.class);
                startActivity(searchPageIntent);
            }
        });
    }
}
