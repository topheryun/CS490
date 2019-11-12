package ProjectRF;
import java.awt.image.BufferedImage;
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

public class Main {
//	public static void main(String[] args) throws Exception {
//	    AccessDatabase dao = new AccessDatabase();
//	    dao.readDataBase();
//	  }
	   public static void main(String[] args) throws Exception 
	   {
		   ConnectToDatabase();
		   //AccessDatabase.ReadDatabase();
	   }
	   
	   public static void ConnectToDatabase()
	   {
	      String JdbcURL = "jdbc:mysql://localhost:3306/rfdb";
	      String Username = "root";
	      String password = "root";
	      Connection con = null;
	      try {
	         con = DriverManager.getConnection(JdbcURL, Username, password);
	         Recipe recipe = RecipeDriver();

	         
	         
	         
//		         String insertQuery = "INSERT INTO `rfdb`.`recipetable`"
//		         		+ "(_recipeName, _ingredient1, _ingredient2, _rating)" 
//		        		+ "VALUES ('recipe1', 'tomato', 'onion', 4)";
	         String insertQuery = String.format("INSERT INTO `rfdb`.`recipetable`"
		         		+ "(_recipeName, _ingredient1, _ingredient2, _ingredient3, _ingredient4, _ingredient5, _ingredient6, _ingredient7, _ingredient8, _ingredient9, _ingredient10, _rating)" 
		        		+ "VALUES ('%s', '%s', '%s', '%s', '%s','%s','%s','%s','%s','%s','%s', 5)", 
		        		recipe.name, recipe.getIngredients().get(0), recipe.getIngredients().get(1), recipe.getIngredients().get(2), 
		        		recipe.getIngredients().get(3), recipe.getIngredients().get(4), 
		        		recipe.getIngredients().get(5), recipe.getIngredients().get(6), 
		        		recipe.getIngredients().get(7), recipe.getIngredients().get(8), recipe.getIngredients().get(9));
	         
	         
	        Statement statement = con.createStatement();
	        statement.executeUpdate(insertQuery);
	         
	         
	         System.out.println("Connected to MySQL database");
	         System.out.println("Database Updated");		         
		  } catch (Exception e) 
		  {
			 System.out.println("Failed");
	         e.printStackTrace();
	      }
	   }
	   
	   
	   
	   
	   public static Recipe RecipeDriver() throws Exception 
		{
			// Put websites in an array and go through them
			 URL url = new URL("https://www.allrecipes.com/recipe/11466/");
		    
//		   	for (Integer month = 1; month < 12; month++)
//		   	{
//		   		for (Integer day = 1; day < 31; day++)
//		   		{
//		   			String monthString = month.toString();
//		   			String dayString = day.toString();
//		   			String urlString = "https://www.allrecipes.com/recipe/2019" + monthString + dayString + "//";
//					URL url = new URL(urlString);
//		   		
//		   		}
//		   	}
//		     
		   	// Url searches date -> check if url.toString contains hyphen; if so, its valid url, if not, skip, new date in loop 
		   	// Database: add recipe ID
		     
			 PrintWriter writer = new PrintWriter("webpage.txt", "UTF-8");
			 List<String> list = new ArrayList<String>();;
			 BufferedReader read = new BufferedReader(
		     new InputStreamReader(url.openStream()));
		     String i;
		     String name = null;
		     int rcount = 0;
		     double rscore = 0.0;
		     
		     while ((i = read.readLine()) != null) {
		    	if(i.contains("<title>")) {
		    		name = i.substring(11, i.length()-32);
		    	}
		    	if(i.contains("og:image")) {
		    		SaveImage(i.substring(47,113), "recipeimage.jpg");
		    	}
		    	if(i.contains("checkList__item")&&i.contains("[true]")) {
		     writer.write(i.substring(81, i.length()-2)+"\n");
		     list.add(i.substring(81, i.length()-2));
		    	 }
		    	if(i.contains("itemprop")&&i.contains("reviewCount")) {
		    		rcount = (int) Double.parseDouble(i.substring(50, i.length()-2));
		    	}
		    	if(i.contains("itemprop")&&i.contains("ratingValue")&&rscore == 0) {
		    		rscore = Double.parseDouble(i.substring(50, i.length()-2));
		    	}
		     }
		     BufferedImage originalImage = ImageIO.read(new File("recipeimage.jpg"));
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ImageIO.write( originalImage, "jpg", baos );
		     baos.flush();
		     byte[] imageInByte = baos.toByteArray();
		     baos.close();
		     ByteBuffer buf = ByteBuffer.wrap(imageInByte);
		     Recipe recipe;
		     recipe = new Recipe(name,list,rscore,rcount,buf);
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
		
	   
	   
} 
