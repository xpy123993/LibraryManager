package common;

import java.io.*;
import java.util.ArrayList;

import struct.*;

public class Test {

	static ObjectOutputStream oos = null;
	
	public static void main(String[] args) throws Exception{

		oos = new ObjectOutputStream(new FileOutputStream("C:/Users/ÅôÓî/Desktop/JAVA/Java´ó×÷Òµ/Library.dat"));
		ArrayList<Book> bookSet = new ArrayList<Book>();
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		bookSet.add(new Book(1001, "Java Book", "Java Author", 1.0));
		bookSet.add(new Book(1002, "C++ Book", "C++ Author", 1.0));
		bookSet.add(new Book(1003, "Python Book", "Python Author", 1.0));
		bookSet.add(new Book(1004, "Perl Book", "Perl Author", 1.0));
		oos.writeObject(bookSet);
		oos.close();
		
		System.out.println("Sample File Generate Over.");
	}
	
}
