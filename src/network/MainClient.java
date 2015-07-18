package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import struct.Book;

import common.CONSTANT;

/**
 * 图书管理系统 客户端
 * 
 * @author 鹏宇
 * 
 */
public class MainClient {

	private Socket client = null;

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public boolean connectTo(String IPAddress) {
		try {
			client = new Socket(IPAddress, CONSTANT.SERVERPORT);

			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());

			int success = ois.readInt();

			return success == 0;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean unreachble() {
		return client == null || client.isClosed();
	}

	private void sendInt(int i) {
		try {
			oos.writeInt(i);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendBook(Book book) {
		try {
			oos.writeObject(book);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendString(String text) {
		try {
			oos.writeUTF(text);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int readInt() {
		try {
			return ois.readInt();
		} catch (Exception e) {
			return -1;
		}
	}

	private ArrayList<Book> readBookArray() {

		try {
			@SuppressWarnings("unchecked")
			ArrayList<Book> bookSet = (ArrayList<Book>) ois.readObject();
			return bookSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void AddBook(Book book) {
		sendInt(CONSTANT.ADDBOOK);
		sendBook(book);
	}

	public void DelBook(int index) {
		sendInt(CONSTANT.DELBOOK);
		sendInt(index);
	}

	public void EditBook(int index, Book book) {
		sendInt(CONSTANT.EDITBOOK);
		sendInt(index);
		sendBook(book);
	}

	public ArrayList<Book> FindBook(String bookName) {
		sendInt(CONSTANT.FINDBOOK);
		sendString(bookName);
		return readBookArray();
	}

	public ArrayList<Book> ListBook(int pageIndex) {
		sendInt(CONSTANT.LISTBOOK);
		sendInt(pageIndex);
		return readBookArray();
	}

	public int getBookAmount() {
		sendInt(CONSTANT.BOOKAMOUNT);
		return readInt();
	}

	public void Close() {
		if (client != null && client.isConnected()) {
			sendInt(-1);
			try {
				client.close();
			} catch (Exception e) {

			}
		}

	}
}
