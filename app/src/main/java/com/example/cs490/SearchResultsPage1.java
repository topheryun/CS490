package com.example.cs490;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResultsPage1 extends AppCompatActivity {

    ArrayList<String> searchBasket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_page1);

        searchBasket = getIntent().getStringArrayListExtra("key1");

        ArrayList<String> perfectMatchList = new ArrayList<>();
        ArrayList<String> perfectMatchUrlList = new ArrayList<>();
        perfectMatchList.add("Chocolate Chip Pumpkin Bread");
        perfectMatchUrlList.add("https://www.allrecipes.com/recipe/6732/chocolate-chip-pumpkin-bread/");
        perfectMatchList.add("Chocolate Mexican Wedding Cookies");
        perfectMatchUrlList.add("https://www.allrecipes.com/recipe/9843/chocolate-mexican-wedding-cookies/");
        perfectMatchList.add("Triple Chocolate Chunk Cookie");
        perfectMatchUrlList.add("https://www.allrecipes.com/recipe/11239/triple-chocolate-chunk-cookie/");
        perfectMatchList.add("Apricot and White Chip Cookies with Almonds");
        perfectMatchUrlList.add("https://www.allrecipes.com/recipe/11655/apricot-and-white-chip-cookies-with-almonds/");

        // create object of listview
        ListView listView = findViewById(R.id.listview1);

        // create ArrayList of String
        ArrayList<String> arrayList = new ArrayList<>();

        // Add elements to arraylist
        for(int i = 0; i < perfectMatchList.size(); i++) {
            arrayList.add(perfectMatchList.get(i));
        }

        // create adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arrayList);

        //assign adapter to listview
        listView.setAdapter(arrayAdapter);

        //add listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchResultsPage1.this,
                        "clicked item:"+i+""+arrayList.get(i).toString(),
                        Toast.LENGTH_SHORT).show();


                Intent j = new Intent(Intent.ACTION_VIEW);
                j.setData(Uri.parse(perfectMatchUrlList.get(i)));
                startActivity(j);
            }
        });

            // output test
        TextView tv = findViewById(R.id.testView1);
        String list = searchBasket.get(0);
        for (int i = 1; i < searchBasket.size(); i++) {
            list = list + " , " + searchBasket.get(i);
        }
        tv.setText("Your Basket : " +  list);



        Button btn = findViewById(R.id.page1button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchResultsPage2.class);
                intent.putStringArrayListExtra("key2", searchBasket);
                startActivity(intent);
            }
        });
    }


}
