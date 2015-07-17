package network;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

import struct.*;
import common.CONSTANT;
import common.Log;

/**
 * 图书管理系统 客户容器
 * @author 鹏宇
 *
 */
public class ClientAdapter implements ServerInterface{
	
	private Library library = null;
	
	class ServiceThread extends Thread{
		
		private Socket client = null;
		private ObjectInputStream ois = null;
		private ObjectOutputStream oos = null;
		
		private void generateLog(String logText){
			Log.addLog("IP:" + client.getInetAddress().getHostAddress() + " " + logText);
		}
		
		ServiceThread(Socket target){
			
			try{
				client = target;
				
				oos = new ObjectOutputStream(target.getOutputStream());
				ois = new ObjectInputStream(target.getInputStream());
				
				oos.writeInt(0);
				oos.flush();
				
				generateLog("Login");
				
			}catch(Exception e){
				perror("Initialize", e);
			}
			
		}
		
		int readInt(){
			try{
				return ois.readInt();
			}catch(Exception e){
				return -1;
			}
			
		}
		
		Book readBook(){
			try{
				return (Book) ois.readObject();
			}catch(Exception e){
				perror("ReadBook", e);
			}
			return null;
		}
		
		String readString(){
			try{
				return ois.readUTF();
			}catch(Exception e){
				perror("ReadString", e);
			}
			return "";
		}
		
		void sendInt(int i){
			try{
				oos.writeInt(i);
				oos.flush();
			}catch(Exception e){
				perror("SendInt", e);
			}
		}
		
		void sendString(String str){
			try{
				oos.writeUTF(str);
				oos.flush();
			}catch(Exception e){
				perror("SendString", e);
			}
		}
		
		void sendBook(Book b){
			try{
				oos.writeObject(b);
				oos.flush();
			}catch(Exception e){
				perror("SendBook", e);
			}
		}
		
		void SendBookArray(ArrayList<Book> bookSet){
			sendInt(bookSet.size());
			for(Book book : bookSet)
				sendBook(book);
		}
		
		
		
		@Override
		public void run(){
			
			int command = -1;
			int oper_index = -1;
			
			Book b = null;
			String t = null;
			
			while(client.isConnected()){
				
				command = readInt();
				
				if(command == -1)break;
				
				switch(command){
				case CONSTANT.LISTBOOK:
					SendBookArray(library.getBookList());
					generateLog("Get booklist");
					break;
				case CONSTANT.ADDBOOK:
					b = readBook();
					library.addBook(b);
					generateLog("Add new book info,BookName:" + b.getBookName());
					break;
				case CONSTANT.DELBOOK:
					oper_index = readInt();
					generateLog("Delete a book info,BookID:" + library.getBookID(oper_index));
					library.delBook(oper_index);
					break;
				case CONSTANT.EDITBOOK:
					oper_index = readInt();
					library.editBook(oper_index, readBook());
					generateLog("Edit a book info,BookID:" + library.getBookID(oper_index));
					break;
				case CONSTANT.FINDBOOK:
					t = readString();
					SendBookArray(library.findBook(t));
					generateLog("Search book,search name:" + t);
					break;
					
					default:
						System.out.println("ClientAdapter:Unkown Command :" + command);
				}
				
			}
			generateLog("Logout");
			
		}
		
	}
	
	public void perror(String tag, Exception e){
		Log.addLog("Error occured in function " + tag + ":");
		Log.addLog("---" + e.getMessage());
		Log.onAddLog();
	}
	
	@Override
	public void service(Socket s){
		new ServiceThread(s).start();
	}
	
	public ClientAdapter(Library library){
		this.library = library;
	}
}
