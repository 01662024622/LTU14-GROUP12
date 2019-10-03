package vn.group14.object;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseTVL {
	private static List<TVL> lists = new ArrayList<TVL>();
	private ParseTVL() {}
	private static ParseTVL instance = new ParseTVL();
	
	public static ParseTVL getInstance() {
		return instance;
	}
	public void clear() {
		lists.clear();
	}
	public void add(TVL e) {
		lists.add(e);
	}
	public List<TVL> getListTVL(){
		return lists;
	}
	/**
	 * add some TVL to list use recursion
	 * @param bytes[]
	 */
	public void addByByte(byte[] bytes) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		DataInputStream data = new DataInputStream(byteStream);
		TVL tvl = new TVL();
		try {
			tvl.setTag(data.readShort());
			tvl.setLength(data.readShort());
			byte[] string = new byte[tvl.getLength()];
			data.readFully(string, 0, string.length);
			tvl.setValue(new String(string));
			lists.add(tvl);
			 byte[] array = new byte[byteStream.available()];
			 byteStream.read(array);
			if (array.length>0) {
				addByByte(array);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
