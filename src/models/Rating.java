package models;

public class Rating {
	public long userID;
	public long bookID;
	public long bookRating;
	public long userTimestamp;

	public Rating(long userID, long bookID, long bookRating, long userTimestamp)
	{
		this.setUserID(userID);
		this.setBookID(bookID);
		this.setBookRating(bookRating);
		this.setUserTimestamp(userTimestamp);
	}

	public long getUserTimestamp() {
		return userTimestamp;
	}

	public void setUserTimestamp(long userTimestamp) {
		this.userTimestamp = userTimestamp;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getBookID() {
		return bookID;
	}

	public void setBookID(long bookID) {
		this.bookID = bookID;
	}

	public long getBookRating() {
		return bookRating;
	}

	public void setBookRating(long bookRating) {
		this.bookRating = bookRating;
	}

	public String toString()
	{
		return  Long.toString(userID) + ' ' + Long.toString(bookID)  + ' ' + Long.toString(bookRating) + ' ' + Long.toString(userTimestamp);
	}

}
