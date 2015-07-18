/**
 * 
 */
package struct;

import java.io.Serializable;

/**
 * @author ≈Ù”Ó
 * 
 * This Class has 4 main elements
 * 
 * BookID : Integer
 * BookName : String
 * Author : String
 * Cost : Double
 *
 */
public class Book implements Serializable ,Comparable<Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4180720458773066890L;
	private int bookID;
	private String bookName, bookAuthor;
	private double cost;
	
	public Book(){}
	
	public Book(int bookID, String bookName, String bookAuthor,double cost){
		this.bookID = bookID;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.cost = cost;
	}
	
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void copyFrom(Book b){
		bookName = "" + b.bookName;
		bookAuthor = "" + b.bookAuthor;
		bookID = b.bookID;
		cost = b.cost;
	}
	
	@Override
	public int compareTo(Object o){
		if(o instanceof Book){
			Book b = (Book) o;
			return bookID - b.bookID;
		}
		return -1;
	}
}
