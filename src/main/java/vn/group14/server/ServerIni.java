package vn.group14.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import vn.group14.enums.Tags;
import vn.group14.object.GetLengthByteHeader;
import vn.group14.object.Headers;
import vn.group14.object.Letters;
import vn.group14.object.ParseTVL;
import vn.group14.object.SystemList;
import vn.group14.object.TVL;
import vn.group14.object.User;
import vn.group14.util.HandelRequest;
import vn.group14.util.ParseObject;

public class ServerIni implements Runnable {
	private Socket socket;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private Status status = Status.INIT;

	public ServerIni(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.run();
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Client accepted");
			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));

			out = new DataOutputStream(socket.getOutputStream());
			boolean check = true;
			while (check) {
				try {
					byte[] headers = new byte[GetLengthByteHeader.lengthByte()];
					in.readFully(headers, 0, headers.length);
//					convert header to object because header have only primitive fields
					Headers header = ParseObject.toObject(headers, Headers.class);
//					byte[] of list TVL
					byte[] tvls = new byte[header.getLengthOfMessage() - GetLengthByteHeader.lengthByte()];
					in.readFully(tvls, 0, tvls.length);
					ParseTVL.getInstance().addByByte(tvls);
					List<TVL> tvlsList = ParseTVL.getInstance().getListTVL();
					Letters letter = new Letters(header, tvlsList);
					System.out.println("Request ");
					System.out.print(header.toString());
					for (int i = 0; i < tvlsList.size(); i++) {
						System.out.print(" " + tvlsList.get(i).toString() + " ");
					}
					System.out.println();
//					process 2.1 check phone number
					if (!HandelRequest.validatePhone(letter)) {
						out.write(Response.responERR());
						check = false;
						break;
					}

					List<TVL> tvlres = new ArrayList<TVL>();
					TVL phone = getPhoneElement(tvlsList);
					TVL name = getNameElement(tvlsList);
					switch ((int) header.getCmdCode()) {
//					2.2 for check authen
					case 0:
						tvlres.clear();
						tvlres.add(phone);
						if (HandelRequest.validateKey(letter)) {
							this.status = Status.READY;
							out.write(Response.responAuthen(tvlres, "OK"));
						} else {
							out.write(Response.responAuthen(tvlres, "NOK"));
							check = false;
						}
						break;
//						2.3 for process 
//						insert
					case 1:
						if (checkStatus(Status.READY)) {
							tvlres.clear();
							tvlres.add(phone);
							out.write(Response.responSuccess(tvlres, header.getCmdCode(), "NOK"));
							check = false;
						} else {
							User user = new User(phone.getValue(), name.getValue());
							SystemList.add(user);
							tvlres.clear();
							tvlres.add(phone);
							out.write(Response.responSuccess(tvlres, header.getCmdCode(), "OK"));
						}
						break;
					case 2:
						if (checkStatus(Status.READY)) {
							tvlres.clear();
							tvlres.add(phone);
							out.write(Response.responSuccess(tvlres, header.getCmdCode(), "NOK"));
							check = false;
						} else {
							this.status = Status.SELECT;
							tvlres.clear();
							tvlres.add(phone);
							out.write(Response.responSuccess(tvlres, header.getCmdCode(), "OK"));
						}
						break;
					default:
						if (!checkStatus(Status.SELECT) && SystemList.getUser(phone.getValue()) == null) {
							tvlres.clear();
							tvlres.add(phone);
							out.write(Response.responSuccess(tvlres, header.getCmdCode(), "NOK"));
						} else {
							tvlres.clear();
							tvlres.add(phone);
							tvlres.add(SystemList.getUser(phone.getValue()));
							out.write(Response.responSuccess(tvlres, header.getCmdCode(), "OK"));
						}
						check = false;
						break;
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
			}
		} catch (IOException i) {
			System.out.println(i);
		} finally {
			try {
				socket.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean checkStatus(Status status) {
		if (this.status == status) {
			return true;
		}
		return false;
	}

	private TVL getPhoneElement(List<TVL> tvls) {
		TVL phone = null;
		for (int i = 0; i < tvls.size(); i++) {
			if (tvls.get(i).getTag() == (short) (Tags.valueOf("PhoneNumber").ordinal())) {
				phone = tvls.get(i);
				break;
			}
		}
		return phone;
	}

	private TVL getNameElement(List<TVL> tvls) {
		TVL name = null;
		for (int i = 0; i < tvls.size(); i++) {
			if (tvls.get(i).getTag() == (short) (Tags.valueOf("Name").ordinal())) {
				name = tvls.get(i);
				break;
			}
		}
		return name;
	}
}
