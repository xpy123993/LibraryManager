package common;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;;

/**
 * ͼ�����ϵͳ ��־��¼����
 * @author ����
 *
 */
public class Log {
	
	private static BufferedWriter writer = null;
	private static LinkedBlockingQueue<String> messageQueue = null;
	private static boolean EditLock = false;
	
	public static void initialize(){
		try{
			writer = new BufferedWriter(new FileWriter(CONSTANT.LOGFILE, true));
			messageQueue = new LinkedBlockingQueue<String>();
		}catch(Exception e){
			System.err.println("���ش����޷���¼��־");
		}
	}
	
	private static void writeLine(String str){
		try{
			writer.write(str + "\r\n");
			writer.flush();
				
			System.out.println(str);
		}catch(Exception e){
			System.err.println("���ش����޷���¼��־");
		}
	}
	
	private static String getTimeLabel(){
		
		Date currentTime = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss] ").format(currentTime);
	}
	
	public static void onAddLog(){
		if(EditLock)return;
		EditLock = true;
		
		while(!messageQueue.isEmpty())
			writeLine(messageQueue.poll());
		
		EditLock = false;
	}
	
	public static void addLog(String logText){
		messageQueue.add(getTimeLabel() + logText);
		onAddLog();
	}
}
