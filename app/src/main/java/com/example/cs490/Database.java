package com.example.cs490;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    public static void SearchDatabase(Connection con, ArrayList<String> basket)
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
                        //TextView tv2 = findViewById(R.id.testView2);
                        //tv2.setText(tableData.getString(2));
                    }
                }
            }
        } catch (Exception e)
        {
            Log.d("debugtag", "Search didn't work: " + e);
            System.out.println("Search: Didn't work " + e);
        }

    }

    public static Connection ConnectToDatabase()
    {
        String JdbcURL = "jdbc:mysql://127.0.0.1:3306/rfdb";
        String JdbcURL2 = "jdbc:mysql://localhost:3306/rfdb";
        String Username = "root";
        String Password = "root";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(JdbcURL, Username, Password); // jdbc
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
