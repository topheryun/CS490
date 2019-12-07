package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class IngredientSearch extends AppCompatActivity {

    ListView search_food;
    ArrayAdapter<String> adapter;
    ArrayList<String> basket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search);

        search_food = findViewById(R.id.search_food);
        final TextView tv = findViewById(R.id.tv);
        ArrayList<String> arrayFood = new ArrayList<>();
        arrayFood.addAll(Arrays.asList(getResources().getStringArray(R.array.my_foods)));
        adapter = new ArrayAdapter<>(
                IngredientSearch.this,
                android.R.layout.simple_list_item_1,
                arrayFood
        );

        search_food.setAdapter(adapter);
        search_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                if(!basket.contains(selectedItem) && basket.size() <= 40) {
                    basket.add(selectedItem);
                }
                else if(basket.contains(selectedItem) && basket.size() <= 40) {
                    basket.remove(selectedItem);
                }

                String list = basket.get(0);
                for (int i = 1; i < basket.size(); i++) {
                    list = list + " , " + basket.get(i);
                }
                // Display the selected item text on TextView
                tv.setText("Your Basket : " +  list);
            }
        });

        //final ArrayList<String> temp = new ArrayList<>();
        Button searchResultsButton = findViewById(R.id.search_button);
        searchResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //temp.addAll(basket);
                Intent searchResultsIntent1 = new Intent(getApplicationContext(), SearchResultsPage1.class);
                searchResultsIntent1.putStringArrayListExtra("key1", basket);
                startActivity(searchResultsIntent1);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_food);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}
