package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IngredientSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search);

        // Search button -> search results page
        Button searchButton = findViewById(R.id.button_searchForRecipe);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchResultPageIntent = new Intent(getApplicationContext(), SearchResultsPage.class);
                startActivity(searchResultPageIntent);
            }
        });

// Temp search bar that holds the items that have been searched
        final EditText searchView = findViewById(R.id.searchView);
        final TextView searchIngredientResult = findViewById(R.id.text_searchIngredientResults);
        searchView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    String searchInput = searchView.getText().toString();
                    String tempStr = searchIngredientResult.getText() + ", " + searchInput;
                    searchIngredientResult.setText(tempStr);
                    searchView.setText("");
                    return true;
                }
                return false;
            }
        });
    }
}
