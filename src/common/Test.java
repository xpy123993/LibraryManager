package common;

import java.io.*;

import struct.*;

public class Test {

	static ObjectOutputStream oos = null;
	
	public static void main(String[] args) throws Exception{
		
		oos = new ObjectOutputStream(new FileOutputStream(CONSTANT.LIBRARYPATH));
		oos.writeInt(4);
		oos.writeObject(new Book(1001, "Java Book", "Java Author", 1.0));
		oos.writeObject(new Book(1002, "C++ Book", "C++ Author", 1.0));
		oos.writeObject(new Book(1003, "Python Book", "Python Author", 1.0));
		oos.writeObject(new Book(1004, "Perl Book", "Perl Author", 1.0));
		oos.close();
		
		System.out.println("Sample File Generate Over.");
	}
	
}
