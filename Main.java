package ProjectRF;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import java.util.ArrayList;

public class Main 
{

	   public static void main(String[] args) throws Exception 
	   {
		   Connection con = ConnectToDatabase();
		   PrintWriter log = new PrintWriter("fail-log.txt", "UTF-8");
		   SearchDatabase(con, /*ArrayList basket*/);
			 // ID = starts at 6663 and goes up to 6-digits
		    
		    //Integer startId = 6770;
//		   Integer startId = 6725;
//		   	for (Integer id = startId; id < startId + 100; id++) 
//		   	{
//   				String urlString = "https://www.allrecipes.com/recipe/" + id + "/";
//   				URL url = new URL(urlString);
//   				try 
//   				{
//   					new InputStreamReader(url.openStream());
//   				
//   				} catch (Exception e)
//   				{
//   					System.out.println("Main: Exception: " + id);	
//   				}
//   					Recipe recipe = RecipeDriver(url, id.toString());
//					//Integer amountOfIngredients = recipe.ingredients.size();		   
//					InsertToDatabase(con, recipe, log);	
//	   		
//		   	}
		   	
		   System.out.println("Database Updated");	
		   System.out.println("Done");	       
		        log.close();
				
	   }
	   
	   public static void InsertToDatabase(Connection con, Recipe recipe, PrintWriter log) throws FileNotFoundException, UnsupportedEncodingException
	   {
		   try
		   {
		       String insertQuery = String.format("INSERT INTO `rfdb`.`recipetable`"
		         		+ "(_recipeName, "
		         		+ "_ingredient1, _ingredient2, _ingredient3, _ingredient4, _ingredient5, "
		         		+ "_ingredient6, _ingredient7, _ingredient8, _ingredient9, _ingredient10,"
		         		+ "_ingredient11, _ingredient12, _ingredient13, _ingredient14, _ingredient15,"
		         		+ "_ingredient16, _ingredient17, _ingredient18, _ingredient19, _ingredient20,"
		         		+ "_ingredient21, _ingredient22, _ingredient23, _ingredient24, _ingredient25, "
		         		+ "_ingredient26, _ingredient27, _ingredient28, _ingredient29, _ingredient30,"
		         		+ "_ingredient31, _ingredient32, _ingredient33, _ingredient34, _ingredient35,"
		         		+ "_ingredient36, _ingredient37, _ingredient38, _ingredient39, _ingredient40,"
		         		+ " _rating, _numOfReviews, _url, _urlId, _source, "
		         		+ "_prepTime, _cookingTime, _totalTime)"
//		         		+ "_image)" 
		        		+ "VALUES ('%s', "
		        		+ "'%s', '%s', '%s', '%s','%s','%s','%s','%s','%s','%s',"
		        		+ "'%s', '%s', '%s', '%s','%s','%s','%s','%s','%s','%s',"
		        		+ "'%s', '%s', '%s', '%s','%s','%s','%s','%s','%s','%s',"
		        		+ "'%s', '%s', '%s', '%s','%s','%s','%s','%s','%s','%s',"
		        		+ "'%f', '%d', '%s', '%s', '%s',"
		        		+ "'%s', '%s', '%s')",
//		        		+ " '?')", 
		        		recipe.name, recipe.getIngredients().get(0), 
		        		recipe.getIngredients().get(1), recipe.getIngredients().get(2), 
		        		recipe.getIngredients().get(3), recipe.getIngredients().get(4), 
		        		recipe.getIngredients().get(5), recipe.getIngredients().get(6), 
		        		recipe.getIngredients().get(7), recipe.getIngredients().get(8), 
		        		recipe.getIngredients().get(9), recipe.getIngredients().get(10), 
		    		   	recipe.getIngredients().get(11), recipe.getIngredients().get(12), 
		    		   	recipe.getIngredients().get(13), recipe.getIngredients().get(14), 
		    		   	recipe.getIngredients().get(15), recipe.getIngredients().get(16),
		    		   	recipe.getIngredients().get(17), recipe.getIngredients().get(18),
		    		   	recipe.getIngredients().get(19), recipe.getIngredients().get(20),
		    		   	recipe.getIngredients().get(21), recipe.getIngredients().get(22),
		    		   	recipe.getIngredients().get(23), recipe.getIngredients().get(24),
		    		   	recipe.getIngredients().get(25), recipe.getIngredients().get(26),
		    		   	recipe.getIngredients().get(27), recipe.getIngredients().get(28),
		    		   	recipe.getIngredients().get(29), recipe.getIngredients().get(30),
		    		   	recipe.getIngredients().get(31), recipe.getIngredients().get(32),
		    		   	recipe.getIngredients().get(33), recipe.getIngredients().get(34),
		    		   	recipe.getIngredients().get(35), recipe.getIngredients().get(36),
		    		   	recipe.getIngredients().get(37), recipe.getIngredients().get(38),
		    		   	recipe.getIngredients().get(39), 
		    		   	recipe.grade.rating, recipe.grade.numReviews, recipe.url, recipe.urlId, "allrecipe",
		    		   	recipe.prepTime, recipe.cookingTime, recipe.totalTime);
//		    		   	recipe.image);
	       	       
	       		Statement statement = con.createStatement();
	       		statement.executeUpdate(insertQuery);
	       		
		   }catch (Exception e)
		   {
				 System.out.println("Failed to insert to database");
				 log.write(recipe.url + "\n");
		         e.printStackTrace();			   
		   }
		   
	   }
	   
	   
	   public static Connection ConnectToDatabase()
	   {
	      String JdbcURL = "jdbc:mysql://localhost:3306/rfdb";
	      String Username = "root";
	      String password = "root";
	      Connection con = null;
	      try {
	         con = DriverManager.getConnection(JdbcURL, Username, password);        
	         
	         System.out.println("Connected to MySQL database");
	        	         
		  } catch (Exception e) 
		  {
			 System.out.println("Failed");
	         e.printStackTrace();
	         con = null;
	      }
	      
	      return con;
	   }
	   
	   
	   public static Recipe RecipeDriver(URL url, String urlId) throws Exception 
	   {
			// Put websites in an array and go through them
			//URL url = new URL("https://www.allrecipes.com/recipe/11466/");
		 
		   	// Url searches date -> check if url.toString contains hyphen; if so, its valid url, if not, skip, new date in loop 
		   	// Database: add recipe ID
		     
			 PrintWriter writer = new PrintWriter("webpage.txt", "UTF-8");
			
			 List<String> list = new ArrayList<String>();
			 BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			 String lineRead;
		     String name = null;
		     String prepTime = null;
		     String cookingTime = null;
		     String totalTime = null;
		     int rcount = 0;
		     double rscore = 0.0;
		     
		     while ((lineRead = read.readLine()) != null) 
		     {
		    	// Title
		    	if(lineRead.contains("<title>")) 
		    	{
		    		String nameTemp = lineRead.substring(lineRead.indexOf(">", 0)+1, lineRead.lastIndexOf("<", lineRead.length())-16 );
		    		System.out.println(nameTemp);
		    		if (nameTemp.contains("'"))
		    		{
		    			name = nameTemp.substring(0, nameTemp.indexOf("'", 0)) + "''" + nameTemp.substring(nameTemp.indexOf("'", 0)+1, nameTemp.length()); 
		    		}
		    		else
		    		{
		    			name = nameTemp;
		    		}
		    	}
		    	//Image
		    	if(lineRead.contains("og:image")) 
		    	{
		    		// This errors out for images: 47, 113
		    		String imageString = lineRead.substring(lineRead.indexOf("http", 0), lineRead.lastIndexOf("\"", lineRead.length()-1));
		    		SaveImage(imageString, "recipeimage" + urlId + ".jpg");		    			
		    	}
		    	// Ingredients
		    	if(lineRead.contains("checkList__item") && lineRead.contains("[true]")) 
		    	{
		    		writer.write(lineRead.substring(81, lineRead.length()-2)+"\n");
		    		list.add(lineRead.substring(81, lineRead.length()-2));
		    	}
		    	if (lineRead.contains("aria-label") && lineRead.contains("Prep"))
		    	{
		    		prepTime = lineRead.substring(lineRead.indexOf(":", 0)+1, lineRead.lastIndexOf("\"", lineRead.length()));
		    	}
		    	if (lineRead.contains("aria-label") && lineRead.contains("Cook"))
		    	{
		    		cookingTime = lineRead.substring(lineRead.indexOf(":", 0), lineRead.lastIndexOf("\"", lineRead.length()));
		    	}
		    	if (lineRead.contains("aria-label") && lineRead.contains("Ready"))
		    	{
		    		totalTime = lineRead.substring(lineRead.indexOf("Ready in", 0), lineRead.lastIndexOf("\"", lineRead.length()));
		    	}
		    	if(lineRead.contains("itemprop") && lineRead.contains("reviewCount")) {
		    		rcount = (int) Double.parseDouble(lineRead.substring(50, lineRead.length()-2));
		    	}
		    	if(lineRead.contains("itemprop") && lineRead.contains("ratingValue") && rscore == 0) {
		    		rscore = Double.parseDouble(lineRead.substring(50, lineRead.length()-2));
		    	}
		     }
		  
		     
		     while(list.size() < 40)
		     {
		    	 list.add("");
		     }

		     BufferedImage originalImage = ImageIO.read(new File("recipeimage" + urlId + ".jpg"));
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ImageIO.write( originalImage, "jpg", baos );
		     baos.flush();
		     byte[] imageInByte = baos.toByteArray();
		     baos.close();
		     ByteBuffer buf = ByteBuffer.wrap(imageInByte);
		     Recipe recipe;
		     System.out.println(url.toString());
		     
		     recipe = new Recipe(name, list, rscore, rcount, buf, url.toString(), urlId, prepTime, cookingTime, totalTime);
		    // System.out.println(res.toString());
		     writer.close();
		     read.close();
		     return recipe;
		}
			
		public static void SaveImage(String imageUrl, String destinationFile) throws IOException 
		{
		    URL url = new URL(imageUrl);
		    InputStream is = url.openStream();
		    OutputStream os = new FileOutputStream(destinationFile);
		
		    byte[] b = new byte[2048];
		    int length;
		
		    while ((length = is.read(b)) != -1) {
		        os.write(b, 0, length);
		    }
		
		    is.close();
		    os.close();
		}
	   
	   public static String UrlReader(String url)
	   {
	        URL oracle = new URL("http://www.oracle.com/");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
	        return inputLine;
	   }
		
	   
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
			    			System.out.println(tableData.getString(2));
			    		}
				    }
		    	}
		   } catch (Exception e)
		   {
			   System.out.println("Search: Didn't work " + e);
		   }
		  		   
	   }
	   
} 
