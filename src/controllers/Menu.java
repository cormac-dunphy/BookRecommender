package controllers;

import java.io.FileNotFoundException;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

public class Menu {

	//commands for cliche shell which use methods from the Driver class

		public Driver driver = new Driver();

		@Command(description = "Add A New User")
		public void addUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
				@Param(name = "age") Long age, @Param(name = "gender") String gender,
				@Param(name = "occupation") String occupation, @Param(name = "zipCode") long zipCode) 
		{
			driver.addUser(firstName, lastName, age, gender, occupation, zipCode);
		}

		@Command(description = "Add A New Book")
		public void addBook(@Param(name = "title") String title, @Param(name = "date") String date,
				@Param(name = "author") String author) 
		{
			driver.addBook(title, date, author);
		}

		@Command(description = "Add A New Rating")
		public void addRating(@Param(name = "userID") long userID, @Param(name = "bookID") Long bookID,
				@Param(name = "bookRating") Long bookRating) 
		{
			driver.addRating(userID, bookID, bookRating);
		}

		@Command(description = "Remove User")
		public void removeUser(@Param(name = "userID") long userID) 
		{
			driver.removeUser(userID);
		}

		@Command(description = "Get A Book By ID")
		public void getBook(@Param(name = "bookID") long bookID) 
		{
			driver.getBook(bookID);
		}

		@Command(description = "Get Users")
		public void getUsers() 
		{
			driver.getUsers();
		}

		@Command(description = "Get Book Ratings")
		public void getRatings() throws FileNotFoundException 
		{
			driver.getRatings();
		}

		@Command(description = "Get Book By Title")
		public void getBookByTitle(@Param(name = "title") String title) 
		{
			driver.getBookByTitle(title);
		}

		@Command(description = "Get Books By Year")
		public void getBooksByYear(@Param(name="year")String year) 
		{
			driver.getBookByYear(year);
		}

		@Command(description = "List Books")
		public void listBooks() throws FileNotFoundException 
		{
			driver.listBooks();
		}

		@Command(description = "Get User Ratings")
		public void getUserRatings(@Param(name="userID")long userID) throws FileNotFoundException 
		{
			driver.getUserRatings(userID);
		}
		
		@Command(description = "Get Top 5 Books")
		public void getTop5Books() throws FileNotFoundException
		{
			driver.getTop5Books();
		}

		public void menu() throws Exception 
		{
			Menu menu = new Menu();
			Shell shell = ShellFactory.createConsoleShell("MR", "Welcome to Movie Recommender - ?help for instructions", menu);
			shell.commandLoop();
		}
	
}
