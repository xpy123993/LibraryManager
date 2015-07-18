package struct;

import java.io.*;
import java.util.*;

import common.CONSTANT;

public class Library {
	
	private String savfile = "";
	private ArrayList<Book> bookSet = new ArrayList<Book>();
	private PageSplitter<Book> bookPage = new PageSplitter<Book>();
	
	public Library(String savfile){
		this.savfile = savfile;
	}

	public synchronized void load(){
		
		File storeFile = new File(savfile);
		
		if(!storeFile.isFile())
			return;
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeFile));
			Object o = ois.readObject();
			bookSet = (ArrayList<Book>) o;
			ois.close();
			Collections.sort(bookSet);
			bookPage.setSource(bookSet);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public synchronized void store(){
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savfile));
			oos.writeObject(bookSet);
			oos.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public synchronized ArrayList<Book> getBookList(int page) {

		ArrayList<Book> ret = new ArrayList<Book>();
		bookPage.clear();

		for (int i = page * CONSTANT.ITEMPERPAGE; i < (page + 1) * CONSTANT.ITEMPERPAGE && i < bookSet.size(); i++) {
			bookPage.addItemIndex(i);
		}
		return bookPage.getPage();
	}
	
	public synchronized ArrayList<Book> findBook(String bookName){
		ArrayList<Book> result = new ArrayList<Book>();
		bookPage.clear();
		for (int i = 0; i < bookSet.size(); i++) {
			if (bookSet.get(i).getBookName().indexOf(bookName) != -1) {
				bookPage.addItemIndex(i);
			}
		}

		return bookPage.getPage();
	}
	
	public synchronized void editBook(int index, Book b){
		bookPage.set(index, b);
		store();
	}
	
	public synchronized void addBook(Book b){
		bookSet.add(b);
		store();
	}
	
	public synchronized void delBook(int index){
		bookPage.remove(index);
		store();
	}


	public synchronized int getBookSize(){
		return bookSet.size();
	}
	
	public String getBookName(int index){
		return bookPage.get(index).getBookName();
	}
	
	public int getBookID(int index){
		return bookPage.get(index).getBookID();
	}
}
