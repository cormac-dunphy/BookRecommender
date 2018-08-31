package controllers;

import utils.Importer;

public class Main {

	public static Menu menu = new Menu();
	
	public static void main(String args[]) throws Exception
	{
		Importer.ImportUsers();
		Importer.ImportBooks();
		Importer.ImportRatings();
		
		menu.menu();
	}
	
}