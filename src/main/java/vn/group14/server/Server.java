package vn.group14.server;


public class Server {
	 
	public static void main(String args[]) 
	 { 
	         System.out.println("Server started"); 
	    	 @SuppressWarnings("unused")
			ServerFactory server = new ServerFactory(9669);
	 } 	
}
