package com.example.cs490;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SearchResultsPage2 extends AppCompatActivity {

    ArrayList<String> searchBasket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_page2);

        searchBasket = getIntent().getStringArrayListExtra("key2");

        ArrayList<String> partialMatchList = new ArrayList<>();
        ArrayList<String> partialMatchUrlList = new ArrayList<>();
        partialMatchList.add("Basil, Roasted Peppers and Monterey Jack Cornbread ");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/6664/basil-roasted-peppers-and-monterey-jack-cornbread/");
        partialMatchList.add("Mom's Yeast Rolls");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/6665/moms-yeast-rolls/");
        partialMatchList.add("Jalapeno Cheese Bread");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/6669/jalapeno-cheese-bread/");
        partialMatchList.add("Beer Bread I");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/6717/beer-bread-i/");
        partialMatchList.add("Authentic Mexican Tortillas");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/7122/authentic-mexican-tortillas/");
        partialMatchList.add("Pan de Muertos (Mexican Bread of the Dead)");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/7224/pan-de-muertos-mexican-bread-of-the-dead/");
        partialMatchList.add("Mint Buttercream Frosting With Dark Chocolate Glaze");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/7600/mint-buttercream-frosting-with-dark-chocolate-glaze/");
        partialMatchList.add("Queen Elizabeth Cake I");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/7666/queen-elizabeth-cake-i/");
        partialMatchList.add("Bride's Cake");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/7771/brides-cake/");
        partialMatchList.add("Raw Apple Pound Cake");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/7881/raw-apple-pound-cake/");
        partialMatchList.add("Strawberry Cream Roll");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/8000/strawberry-cream-roll/");
        partialMatchList.add("English Trifle");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/8445/english-trifle/");
        partialMatchList.add("Chocolate Cavity Maker Cake");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/8446/chocolate-cavity-maker-cake/");
        partialMatchList.add("Cranberry Sauce");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/9111/cranberry-sauce/");
        partialMatchList.add("Sweet Potato Fluff");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/9224/sweet-potato-fluff/");
        partialMatchList.add("Cashew Caramel Bars");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/9753/cashew-caramel-bars/");
        partialMatchList.add("Chocolate Sandwich Cookies I");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/10000/chocolate-sandwich-cookies-i/");
        partialMatchList.add("Chocolate Mice");
        partialMatchUrlList.add("https://www.allrecipes.com/recipe/10753/chocolate-mice/");

        // create object of listview
        ListView listView = findViewById(R.id.listview2);

        // create ArrayList of String
        ArrayList<String> arrayList = new ArrayList<>();

        // Add elements to arraylist
        for(int i = 0; i < partialMatchList.size(); i++) {
            arrayList.add(partialMatchList.get(i));
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
                Toast.makeText(SearchResultsPage2.this,
                        "clicked item:"+i+""+arrayList.get(i).toString(),
                        Toast.LENGTH_SHORT).show();


                Intent j = new Intent(Intent.ACTION_VIEW);
                j.setData(Uri.parse(partialMatchUrlList.get(i)));
                startActivity(j);
            }
        });

        // output test
        TextView tv = findViewById(R.id.testView2);
        String list = searchBasket.get(0);
        for (int i = 1; i < searchBasket.size(); i++) {
            list = list + " , " + searchBasket.get(i);
        }
        tv.setText("Your Basket : " +  list);

        Button btn = findViewById(R.id.page2button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchResultsPage1.class);
                intent.putStringArrayListExtra("key1", searchBasket);
                startActivity(intent);
            }
        });
    }

}


