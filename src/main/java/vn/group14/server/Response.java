package vn.group14.server;

import java.util.ArrayList;
import java.util.List;

import vn.group14.enums.CmdCode;
import vn.group14.enums.Tags;
import vn.group14.object.Headers;
import vn.group14.object.Letters;
import vn.group14.object.TVL;
import vn.group14.util.CheckData;
import vn.group14.util.HandelRequest;

public class Response {
	public static byte[] response(Headers header,List<TVL> tvls) {
		Letters letter = new Letters(header, tvls);
		return  letter.parseByte();
	}
	public static byte[] responERR() {
		Headers header = new Headers((short)CmdCode.valueOf("ERROR").ordinal());
		List<TVL> tvls = new ArrayList<TVL>();
		TVL tvl = new TVL((short)Tags.valueOf("Result").ordinal(),"NA");
		tvls.add(tvl);
		return response(header,tvls);
	}
	public static byte[] responAuthen(List<TVL> tvls,String result) {
		Headers header = new Headers((short)CmdCode.valueOf("AUTHEN").ordinal());
		TVL tvl = new TVL((short)Tags.valueOf("Result").ordinal(),result);
		tvls.add(tvl);
		return response(header,tvls);
	}
	public static byte[] responSuccess(List<TVL> tvls,short cmd,String result) {
		Headers header = new Headers(cmd);
		TVL tvl = new TVL((short)Tags.valueOf("Result").ordinal(),result);
		tvls.add(tvl);
		return response(header,tvls);
	}
}
