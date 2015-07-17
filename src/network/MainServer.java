package network;

import java.net.ServerSocket;
import java.net.Socket;

import struct.Library;
import common.CONSTANT;
import common.Log;


/**
 * 图书管理系统 服务器
 * 
 * @author 鹏宇
 * @version 1.2
 */
public class MainServer {

	private Library library = null;
	private ServerSocket serverSocket = null;
	
	private boolean bindPort(){
		try{
			serverSocket = new ServerSocket(CONSTANT.SERVERPORT);
			return true;
		}catch(Exception e){
			System.err.println("端口被占用");
		}
		return false;
	}
	
	private Socket getClient(){
		try{
			return serverSocket.accept();
		}catch(Exception e){
			System.err.println("服务端口被关闭");
		}
		return null;
	}
	
	public MainServer(){
		library = new Library(CONSTANT.LIBRARYPATH);
		library.load();
		Log.initialize();
	}

	public void Start(){
		ServerInterface server = new ClientAdapter(library);
		System.out.println("Library Infomation Server");
		System.out.println("Author:Xiepengyu");
		System.out.println();
		System.out.println("Now Books Amount in Library:" + library.getBookSize());
		System.out.println();
		if(bindPort()){
			while(true){
				Socket client = getClient();
				server.service(client);
			}
		}
		System.out.println("服务器已终止");
	}
	
	public static void Show(){
		MainServer mainServer = new MainServer();
		mainServer.Start();
	}
}
