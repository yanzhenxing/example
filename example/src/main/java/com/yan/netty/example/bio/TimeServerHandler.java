package com.yan.netty.example.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

	private Socket socket;
	
	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader in=null;
		PrintWriter out=null;
		
		try {
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream());
			String cuttentTime=null;
			String body=null;
			
			while (true) {
				body=in.readLine();
				if(body==null){
					break;
				}
				System.out.println("The time server receive order : "+body);
				cuttentTime="query time order".equalsIgnoreCase(body)?new Date().toString():"bad order";
				out.println(cuttentTime);
				out.flush();
			}
		} catch (IOException e) {
			if (in!=null) {
				try {
					in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (out!=null) {
				out.close();
				out=null;
			}
			if (socket!=null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				this.socket=null;
			}
		}
	}

}
