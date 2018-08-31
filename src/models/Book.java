package models;

public class Book {
	
	public long id;
	public String title;
	public String date;
	public String author;
	
	public Book(String title, String date, String author)
	{
		this.id = id;
		this.title = title;
		this.date = date;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString()
	{
		return  title + ' ' + date  + ' ' + author;
	}


}
