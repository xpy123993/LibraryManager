package struct;

import java.io.*;
import java.util.*;

public class Library {
	
	private String savfile = "";
	private ArrayList<Book> bookSet = new ArrayList<Book>();
	
	public Library(String savfile){
		this.savfile = savfile;
	}

	public synchronized void load(){
		
		File storeFile = new File(savfile);
		
		if(!storeFile.isFile())
			return;
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeFile));
			int count = ois.readInt();
			while(count-- > 0){
				Object o = ois.readObject();
				if(o instanceof Book)
					bookSet.add((Book) o);
			}
			ois.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public synchronized void store(){
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savfile));
			oos.writeInt(bookSet.size());
			for(Book b : bookSet)
				oos.writeObject(b);
			oos.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized ArrayList<Book> getBookList(){
		Collections.sort(bookSet);
		return bookSet;
	}
	
	public synchronized ArrayList<Book> findBook(String bookName){
		ArrayList<Book> result = new ArrayList<Book>();
		for(Book b : bookSet)
			if(b.getBookName().indexOf(bookName)!=-1)
				result.add(b);
		Collections.sort(result);
		return result;
	}
	
	public synchronized void editBook(int index, Book b){
		Book s = bookSet.get(index);
		s.copyFrom(b);
		store();
	}
	
	public synchronized void addBook(Book b){
		bookSet.add(b);
		store();
	}
	
	public synchronized void delBook(int index){
		bookSet.remove(index);
		store();
	}

	public synchronized int getBookSize(){
		return bookSet.size();
	}
	
	public String getBookName(int index){
		return bookSet.get(index).getBookName();
	}
	
	public int getBookID(int index){
		return bookSet.get(index).getBookID();
	}
}
