package com.example.cs490;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;


public class SearchResultsPage2 extends AppCompatActivity {

    ArrayList<String> searchBasket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_page2);

        searchBasket = getIntent().getStringArrayListExtra("key");
        Log.d("SearchResultsPage2:", "test");

        TextView tv = findViewById(R.id.testView);

        String list = searchBasket.get(0);
        for (int i = 1; i < searchBasket.size(); i++) {
            list = list + " , " + searchBasket.get(i);
        }
        tv.setText("TESTING::OUTPUT::Your Basket : " +  list);

        Log.i("SearchResultsPage2:", "before SearchDatabase method");
        Connection con = ConnectToDatabase();
        SearchDatabase(con, searchBasket);

    }

    public void SearchDatabase(Connection con, ArrayList<String> basket)
    {

        // When getting the String variable of the Basket Ingredient, turn it first to lowercase ALL, then check for all on each column
        try
        {
            String selectQuery = "SELECT * FROM rfdb.recipetable";
            Statement stmt  = con.createStatement();
            ResultSet tableData = stmt.executeQuery(selectQuery);

            // Goes through all basket items each recipe (tableData holds every row as a linked list: ResultSet)
            // Ingredient columns: 4 - 44
            while (tableData.next())
            {
                for (int index = 0, columnNum = 4; index < basket.size(); index++)
                {
                    String ingredientSearched = basket.get(index).toLowerCase();
                    String ingredientSearchedUpperCased = ingredientSearched.substring(0, 1).toUpperCase() + ingredientSearched.substring(1, ingredientSearched.length());

                    if (tableData.getString(columnNum).contains(ingredientSearched) ||
                            tableData.getString(columnNum).contains(ingredientSearchedUpperCased));
                    {
                        Log.d("SearchResultsPage2:", tableData.getString(2));
                        TextView tv2 = findViewById(R.id.testView2);
                        tv2.setText(tableData.getString(2));
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println("Search: Didn't work " + e);
        }

    }

    public static Connection ConnectToDatabase()
    {
        String server = "server=localhost;";
        String database = "database=rfdb;";
        String username = "uid=root;";
        String password = "pwd=root;";
        String myConnectionString = server + database + username + password;
        //MySqlConnection connection = null;

        String JdbcURL = "jdbc:mysql://localhost:3306/rfdb";
        String Username = "root";
        String Password = "root";
        Connection con = null;
        try {
            con = DriverManager.getConnection(JdbcURL, Username, Password); // jdbc
            //con = DriverManager.getConnection(server, username, password);
            Log.i("ConnectionTAG", "Connected to MySQL database");
            System.out.println("Connected to MySQL database");

        } catch (Exception e)
        {
            System.out.println("Failed");

            Log.i("ConnectionTAG", "Failed to Connect: " + e);
            e.printStackTrace();
            con = null;
        }

        return con;
    }

}


