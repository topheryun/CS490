package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IngredientSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search);

        Button searchButton = findViewById(R.id.searchView);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView searchIngredientResult = findViewById(R.id.text_searchIngredientResults);
                EditText searchView = findViewById(R.id.searchView);

                String searchInput = searchView.getText().toString();
                searchIngredientResult.setText(searchInput);
                searchView.setText("");
            }
        });
    }
}
