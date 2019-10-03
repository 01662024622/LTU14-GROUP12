package vn.group14.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.group14.object.GetLengthByteHeader;
import vn.group14.object.Headers;
import vn.group14.object.Letters;
import vn.group14.object.TVL;

public class Test {
	public static void main(String args[]) 
	 { 
		List<TVL> tvls = new ArrayList<TVL>();
		Headers header = new Headers((short) 0, (short) 0);
		tvls.add(new TVL((short) 0, "0987654321"));
		Letters letter = new Letters(header, tvls);
	 } 	
}
