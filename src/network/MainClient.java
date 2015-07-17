package network;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import struct.*;
import common.*;

/**
 * 图书管理系统 客户端
 * @author 鹏宇
 *
 */
public class MainClient {
	
	private Socket client = null;
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public void connectTo(String IPAddress){
		try{
			client = new Socket(IPAddress, CONSTANT.SERVERPORT);
			
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			
			ois.readInt();

		}catch(Exception e){

			e.printStackTrace();

		}
	}
	
	public boolean unreachble(){
		return client == null || client.isClosed();
	}
	
	private void sendInt(int i){
		try{
			oos.writeInt(i);
			oos.flush();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private void sendBook(Book book){
		try{
			oos.writeObject(book);
			oos.flush();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private void sendString(String text){
		try{
			oos.writeUTF(text);
			oos.flush();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private int readInt(){
		try{
			return ois.readInt();
		}
		catch(Exception e){
			return -1;
		}
	}
	
	private Book readBook(){
		try{
			return (Book) ois.readObject();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private ArrayList<Book> readBookArray(){
		ArrayList<Book> bookSet = new ArrayList<Book>();
		int count = readInt();
		while(count-- > 0)
			bookSet.add(readBook());
		return bookSet;
	}
	
	public void AddBook(Book book){
		sendInt(CONSTANT.ADDBOOK);
		sendBook(book);
	}
	
	public void DelBook(int index){
		sendInt(CONSTANT.DELBOOK);
		sendInt(index);
	}
	
	public void EditBook(int index, Book book){
		sendInt(CONSTANT.EDITBOOK);
		sendInt(index);
		sendBook(book);
	}
	
	public ArrayList<Book> FindBook(String bookName){
		sendInt(CONSTANT.FINDBOOK);
		sendString(bookName);
		return readBookArray();
	}
	
	public ArrayList<Book> ListBook(){
		sendInt(CONSTANT.LISTBOOK);
		return readBookArray();
	}
	
	public void Close(){
		if(client != null && client.isConnected()){
			sendInt(-1);
			try{
				client.close();
			}catch(Exception e){
				
			}
			
		}
		
	}
}
