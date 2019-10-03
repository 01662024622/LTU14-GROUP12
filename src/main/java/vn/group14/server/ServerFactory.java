package vn.group14.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerFactory {
	//initialize socket and input stream 
	 private Socket socket = null; 
	 private ServerSocket server = null; 
	 public ServerFactory(int port) 
	 { 
	     // starts server and waits for a connection 
	     try
	     { 
	         server = new ServerSocket(port); 
	         System.out.println("Waiting for a client ..."); 
	         while (true) {
	        	 socket = server.accept();
	        	 new ServerIni(socket);
			}
	     } 
	     catch(IOException i) 
	     { 
	         System.out.println(i); 
	     } 
	 } 
}
