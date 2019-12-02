package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResultsPage2 extends AppCompatActivity {

    ArrayList<String> searchBasket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_page2);

        searchBasket = getIntent().getStringArrayListExtra("key");

        TextView tv = findViewById(R.id.testView);

        String list = searchBasket.get(0);
        for (int i = 1; i < searchBasket.size(); i++) {
            list = list + " , " + searchBasket.get(i);
        }
        tv.setText("TESTING::OUTPUT::Your Basket : " +  list);
    }
}
