package com.yan.netty.example.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {

	public static void main(String[] args) {
		int port =8080;
		if (args!=null && args.length>0) {
			port=Integer.valueOf(args[0]);
		}
		
		Socket socket=null;
		BufferedReader in=null;
		PrintWriter out=null;
		
		try {
			socket=new Socket("127.0.0.1", port);
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream(),true);
			out.println("query time order");
			System.out.println("send order to server succeed. ");
			
			String resp=in.readLine();
			System.out.println("Now is : "+resp);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
				socket=null;
			}
		}
	}

}
