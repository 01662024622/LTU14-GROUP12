package vn.group14.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.group14.object.GetLengthByteHeader;
import vn.group14.object.Headers;
import vn.group14.object.Letters;
import vn.group14.object.ParseTVL;
import vn.group14.object.TVL;
import vn.group14.util.ParseObject;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;

	// constructor to put ip address and port
	public Client(String address, int port) {
		// establish a connection
		try {
			socket = new Socket(address, port);

			// takes input from terminal
			input = new DataInputStream(socket.getInputStream());

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("Connected");
			System.out.println(socket);
			// string to read message from input
			boolean check = true;

			// keep reading until "Over" is input
			while (check) {
				try {
					
					switch (new Random().nextInt(4)) {
					case 0:
//						script for 2.1
						out.write(ex1());
						break;
						
					case 1:
//						script for 2.2
						out.write(ex2());
						break;
					case 2:
//						script for 2.3
						out.write(ex1());
						out.write(ex3());
						break;
					case 3:
//						script for 2.4
						
						out.write(ex1());

						out.write(ex4());

						out.write(ex5());
						break;

					default:
						break;
					}

//					response process
					byte[] headers = new byte[GetLengthByteHeader.lengthByte()];
					input.readFully(headers, 0, headers.length);
//						convert header to object because header have only primitive fields
						try {
							Headers resHeader = ParseObject.toObject(headers, Headers.class);
							byte[] resTvls = new byte[resHeader.getLengthOfMessage() - GetLengthByteHeader.lengthByte()];
							input.readFully(resTvls, 0, resTvls.length);
							ParseTVL.getInstance().addByByte(resTvls);
							List<TVL> tvlsList= ParseTVL.getInstance().getListTVL();
							System.out.println("Response ");
							System.out.print(resHeader.toString());
							for (int i = 0; i < tvlsList.size(); i++) {
								System.out.print(" " +tvlsList.get(i).toString() +" ");
							}
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						
				} catch (IOException i) {
					System.out.println(i);
				}
				check = false;
			}
		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		} finally {
			try {
				input.close();
				out.close();
				socket.close();
			} catch (IOException i) {
				System.out.println(i);
			}
		}

		// close the connection

	}

	public static void main(String args[]) {
		Client client = new Client("127.0.0.1", 9669);
	}
	
	public static byte[] ex1() {
		List<TVL> tvls = new ArrayList<TVL>();
		Headers header = new Headers((short) 0, (short) 0);
		tvls.add(new TVL((short) 0, "topicaasd"));
		tvls.add(new TVL((short) 1, "0986654321"));
		Letters letter = new Letters(header, tvls);
		return  letter.parseByte();
	}
	public static byte[] ex2() {
		List<TVL> tvls = new ArrayList<TVL>();
		Headers header = new Headers((short) 0, (short) 0);
		tvls.add(new TVL((short) 0, "topicaasd"));
		tvls.add(new TVL((short) 1, "0984654321"));
		Letters letter = new Letters(header, tvls);
		return  letter.parseByte();
	}
	
	public static byte[] ex3() {
		List<TVL> tvls = new ArrayList<TVL>();
		Headers header = new Headers((short) 1, (short) 0);
		tvls.add(new TVL((short) 2, "username"));
		tvls.add(new TVL((short) 1, "0984654321"));
		Letters letter = new Letters(header, tvls);
		return  letter.parseByte();
	}
	public static byte[] ex4() {
		List<TVL> tvls = new ArrayList<TVL>();
		Headers header = new Headers((short) 2, (short) 0);
		tvls.add(new TVL((short) 1, "0984654321"));
		Letters letter = new Letters(header, tvls);
		return  letter.parseByte();
	}
	public static byte[] ex5() {
		List<TVL> tvls = new ArrayList<TVL>();
		Headers header = new Headers((short) 3, (short) 0);
		tvls.add(new TVL((short) 1, "0984654321"));
		Letters letter = new Letters(header, tvls);
		return  letter.parseByte();
	}
}




