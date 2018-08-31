package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import models.Book;
import models.Rating;
import models.User;

public class Importer {
	
	public static HashMap<Long, User> userMap = new HashMap<Long, User>();
	public static HashMap<Long, Book> bookMap = new HashMap<Long, Book>();
	public static HashMap<Long, Rating> ratingMap = new HashMap<Long, Rating>();
	public static Long id;
	
	//reads in users file and splits them into tokens
		public static void ImportUsers() throws IOException
		{
			File usersFile = new File("data/users.dat");
			String delims = "[|]";
			Scanner scanner = new Scanner(usersFile);

			while (scanner.hasNextLine()) {
				String userDetails = scanner.nextLine().trim();
				String[] userTokens = userDetails.split(delims);

				//assign each token a name
				id = Long.parseLong(userTokens[0]);
				String firstName = userTokens[1];
				String lastName = userTokens[2];
				long age = Long.parseLong(userTokens[3]);
				String gender = userTokens[4];
				String occupation = userTokens[5];
				long zipCode = Long.parseLong(userTokens[6]);

				User u = new User(id, firstName, lastName, age, gender, occupation, zipCode);
				userMap.put(new Long(id), u);
				System.out.println(u);
			}
			scanner.close();
		}
		
		//reads in books file and splits them into tokens
		public static void ImportBooks() throws FileNotFoundException
		{
			File booksFile = new File("data/books.dat");
			String delims = "[|]";
			Scanner scanner = new Scanner(booksFile);

			while (scanner.hasNextLine())   {    
				String bookDetails = scanner.nextLine().trim();    
				String[] bookTokens = bookDetails.split(delims);
				String bookID = String.valueOf(Long.parseLong(bookTokens[0]));
				String title = bookTokens[1];
				String date = bookTokens[2];
				String author = bookTokens[3];

				Book b = new Book(title, date, author);
				System.out.println(b);
				bookMap.put(new Long(bookID), b);
			}
			scanner.close();
			System.out.println(bookMap);
		}
		
		//reads in ratings file and splits them into tokens
		public static void ImportRatings() throws FileNotFoundException
		{
			File ratingsFile = new File("data/ratings.dat");
			Scanner scanner = new Scanner(ratingsFile);
			String delims = "[|]";

			while (scanner.hasNextLine())   {
				String ratingDetails = scanner.nextLine();
				String[] ratingTokens = ratingDetails.split(delims);
				long userID = Long.parseLong(ratingTokens[0]);
				long movieID = Long.parseLong(ratingTokens[1]);
				long movieRating = Long.parseLong(ratingTokens[2]);
				long userTimestamp = Long.parseLong(ratingTokens[3]);

				Rating r = new Rating(userID, movieID, movieRating, userTimestamp);
				System.out.println(r);
				ratingMap.put(userID, r);
				
			}
			scanner.close();
			System.out.println(ratingMap);
		}
}
