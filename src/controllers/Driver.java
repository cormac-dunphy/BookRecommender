package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import utils.Importer;
import models.Book;
import models.Rating;
import models.User;

public class Driver {
	
	//adds new user to usermap and gives it an id of 1 greater than the size of the hashmap 
	//serializes the hashmap to the file
	public User addUser(String firstName, String lastName, long age, String gender, String occupation, long zipCode)
	{
		//gives user id of 1 more than the size of the hashmap
		long id = Importer.userMap.size()+1;
		User user = new User(id, firstName, lastName, age, gender, occupation, zipCode);
		//puts the user into the hashmap
		Importer.userMap.put(id, user);

		//initializes variables
		File userFile = new File("data/users.dat");
		String fileName = ("data/users.dat");
		String delims2 = "|";
		String newLine = System.getProperty("line.separator");

		// Use new PrintWriter with the 'true' parameter to avoid overwriting the user file.
		// Add the new user info along with the id and delimiter to the userFile
		PrintWriter printWriter = null;
		try {
			//if the user file doesnt exist create one
			if (!userFile.exists()) userFile.createNewFile();
			printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			//write the info to the userFile with delims |
			printWriter.write(newLine + id + delims2 + firstName + delims2 + lastName + delims2 + age + delims2 + gender + delims2 + occupation + delims2 + zipCode);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		//confirmation that you've added the user
		System.out.println("User Added to File: " + firstName + " " + lastName + " " + age + " " + gender + " " + occupation + " " + zipCode );

		return user;
	}

	//removes user
	public void removeUser(long userID) 
	{
		System.out.println("User" + " " + userID + " " + "has been removed");
		Importer.userMap.remove(userID);
		//return Importer.userMap.remove(userID); 
		System.out.println(Importer.userMap);
	}

	//adds new movie to moviemap and gives it an id of 1 greater than the size of the hashmap
	//serializes to the file
	public Book addBook(String title, String date, String author) 
	{
		//gives movie id of one greater than the hashmap size
		long id = Importer.bookMap.size()+1;
		Book book = new Book(title, date, author);
		Importer.bookMap.put(id, book);
		//initializing variables
		File bookFile = new File("data/books.dat");
		String fileName = ("data/books.dat");
		String delims2 = "|";
		String newLine = System.getProperty("line.separator");
		// Use new PrintWriter with the 'true' parameter to avoid overwriting the movies file.
		// Add the new movie info along with the id and delimiters
		PrintWriter printWriter = null;
		try {
			//if movieFile doesnt exist create one
			if (!bookFile.exists()) bookFile.createNewFile();
			printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			//write the movie info to the file with the delims |
			printWriter.write(newLine + id + delims2 + title + delims2 + date + delims2 + author);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		//confirmation that you've added the movie
		System.out.println("Book Added to File: " + title + " " + date + " " + author );
		return book;
	}

	//adds a rating to the ratingmap and serializes to the file
	public Rating addRating(long userID, long bookID, long bookRating) 
	{
		//use built in date.getTime to get the timestamp
		java.util.Date date= new java.util.Date();
		long timestampMS = date.getTime();
		Rating rating = new Rating(userID, bookID, bookRating, timestampMS);
		//puts rating into map with the users ID
		Importer.ratingMap.put(rating.getUserID(), rating);

		// Initialize variables
		File ratingFile = new File("data/ratings.dat");
		String fileName = ("data/ratings.dat");
		String delims2 = "|";
		String newLine = System.getProperty("line.separator");

		// Use new PrintWriter with the 'true' parameter to avoid overwriting the ratings file.
		// Add the new rating info, id and delimiters
		PrintWriter printWriter = null;
		try {
			//if the ratingFile doesn't exist create one
			if (!ratingFile.exists()) ratingFile.createNewFile();
			printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			//writes rating info file with delims |
			printWriter.write(newLine + userID + delims2 + bookID + delims2 + bookRating + delims2 + timestampMS);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
		//confirmation that you've added the rating
		System.out.println("Rating Added to File: " + userID + " " + bookID + " " + bookRating + " " + timestampMS );
		return rating;
	}

	//returns movie by id
	public Book getBook(long bookID) 
	{
		Book b = Importer.bookMap.get(bookID);
		System.out.println(b);
		return b;
	}
	
	public void getUserRatings(long userID) throws FileNotFoundException 
	{
		File ratingsFile = new File("data/ratings.dat");
		Scanner scanner = new Scanner(ratingsFile);
		String delims = "[|]";
		
		while (scanner.hasNextLine())   {
			String ratingDetails = scanner.nextLine();
			String[] ratingTokens = ratingDetails.split(delims);
			long luserID = Long.parseLong(ratingTokens[0]);
			long lbookID = Long.parseLong(ratingTokens[1]);
			long lbookRating = Long.parseLong(ratingTokens[2]);
			long luserTimestamp = Long.parseLong(ratingTokens[3]);

			Rating r = new Rating(luserID, lbookID, lbookRating, luserTimestamp);
			
			if(luserID == userID){
			System.out.println(r);
			}
		}
		
	}
	
	public void getTop5Books() throws FileNotFoundException
	{	
		File ratingsFile = new File("data/ratings.dat");
		Scanner scanner = new Scanner(ratingsFile);
		String delims = "[|]";
		int x = 0;
		long [][] bookratings = new long[1000][2];
		while (scanner.hasNextLine())   {
			String ratingDetails = scanner.nextLine();
			String[] ratingTokens = ratingDetails.split(delims);
			long luserID = Long.parseLong(ratingTokens[0]);
			long lbookID = Long.parseLong(ratingTokens[1]);
			long lbookRating = Long.parseLong(ratingTokens[2]);
			long luserTimestamp = Long.parseLong(ratingTokens[3]);
			
			bookratings[x][0] = lbookID;
			bookratings[x][1] = lbookRating;
			
			x++;
					
		}
		
		Arrays.sort(bookratings, Comparator.comparingLong(arr -> arr[1]));
		
		for(int i=999; i>994; i--) {
	            System.out.print("Book ID : "+bookratings[i][0]);
	            long booknumber = bookratings[i][0];
	            //System.out.print(" book number" + booknumber);
	            //Book book = getBook(booknumber);
	            Book book = Importer.bookMap.get(booknumber);
	            String bookname = book.title;
	            System.out.print("     Book Name: " + bookname);
	            System.out.println("     Book Rating : " +bookratings[i][1]);
	            System.out.println("-----------------------");
	    }
	}
	
	public Book getBookByTitle(String title)
	{	
		//iterates through the values in sortedBookMap
				for(Book value : Importer.bookMap.values())
				{
					//if the inputted string is in the value.title in the hashmap - print out the value
					if(value.title.contains(title))
					{
						System.out.println(value);
					}
				}
				return null;
		
	}
	
	
	public void getUsers()
	{
		for (Map.Entry<Long, User> entry : Importer.userMap.entrySet())
		{
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		}	
	}
	
	
	public void listBooks()
	{
		for (Map.Entry<Long, Book> entry : Importer.bookMap.entrySet())
		{
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		}	
	}
	
	
	public void getRatings() throws FileNotFoundException 
	{
		File ratingsFile = new File("data/ratings.dat");
		Scanner scanner = new Scanner(ratingsFile);
		String delims = "[|]";
		
		System.out.println(String.format("%-6s %-6s %-6s", "User", "Book", "Rating(out of 10)"));
		System.out.println("--------------------------------");
		
		while (scanner.hasNextLine())   {
			String ratingDetails = scanner.nextLine();
			String[] ratingTokens = ratingDetails.split(delims);
			long luserID = Long.parseLong(ratingTokens[0]);
			long lbookID = Long.parseLong(ratingTokens[1]);
			long lbookRating = Long.parseLong(ratingTokens[2]);
			long luserTimestamp = Long.parseLong(ratingTokens[3]);

			Rating r = new Rating(luserID, lbookID, lbookRating, luserTimestamp);
			
			//System.out.println(r);
			System.out.println(String.format("%-6s %-6s %-6s", luserID, lbookID, lbookRating));
			//System.out.println(luserID + "  " + lbookID + "  " + lbookRating);	
		}
	}
	
	public Book getBookByYear(String year)
	{	
		//iterates through the values in sortedBookMap
				for(Book value : Importer.bookMap.values())
				{
					//if the inputted number is in the value.date in the hashmap - print out the value
					if(value.date.contains(year))
					{
						System.out.println(value);
					}
				}
				return null;	
	}
	
	
	public List<Book> top5Books()
	{
		List<Book> fiveBooks = (List<Book>) Importer.bookMap.values();
		//Collections.sort((List<T>) fiveBooks);
		return fiveBooks.subList(1, 10);		
	}
}
